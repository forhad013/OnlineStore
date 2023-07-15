import com.greenrobotdev.onlinestore.data.mapper.asDomainProductEntity
import com.greenrobotdev.onlinestore.data.repository.local.datasource.ProductListLocalDataSource
import com.greenrobotdev.onlinestore.domain.entity.Product
import com.greenrobotdev.onlinestore.shared.cache.StoreDatabase


class ProductListLocalDataSourceImpl(StoreDatabase: StoreDatabase) : ProductListLocalDataSource {

    private val queries = StoreDatabase.storeDatabaseQueries

    override fun getProductListFromLocal(): List<Product> {
        return queries.getProductlist(::asDomainProductEntity).executeAsList()
    }

    override fun insertProductListToDB(products: List<Product>) {
       for(product in products){
           queries.transaction {
               queries.insertProductist(
                   id =  product.id,
                   title = product.title,
                   price = product.price,
                   image = product.image,
                   description = product.description,
                   category = product.category,
                   rate = product.rate,
                   count = product.count.toLong()
               )
           }
       }
    }

    override fun deleteProductListFromDB() {
        return queries.removeProductlist()
    }
}