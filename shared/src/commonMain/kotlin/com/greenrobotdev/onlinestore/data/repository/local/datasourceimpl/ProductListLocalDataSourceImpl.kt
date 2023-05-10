import com.greenrobotdev.onlinestore.data.mapper.asDomainProductEntity
import com.greenrobotdev.onlinestore.data.repository.local.datasource.ProductListLocalDataSource
import com.greenrobotdev.onlinestore.entity.Product
import com.greenrobotdev.onlinestore.shared.cache.StoreDatabase


class ProductListLocalDataSourceImpl(StoreDatabase: StoreDatabase) : ProductListLocalDataSource {

    private val queries = StoreDatabase.storeDatabaseQueries

    override fun getProductListFromLocal(): List<Product> {
        return queries.getMovielist(::asDomainProductEntity).executeAsList()
    }
}