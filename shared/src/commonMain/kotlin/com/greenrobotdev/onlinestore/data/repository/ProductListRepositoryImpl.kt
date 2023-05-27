package com.greenrobotdev.onlinestore.data.repository

import com.greenrobotdev.onlinestore.data.base.Response
import com.greenrobotdev.onlinestore.data.mapper.asDomainModel
import com.greenrobotdev.onlinestore.data.repository.local.datasource.ProductListLocalDataSource
import com.greenrobotdev.onlinestore.data.repository.remote.datasource.ProductListRemoteDataSource
import com.greenrobotdev.onlinestore.domain.entity.Product
import com.greenrobotdev.onlinestore.domain.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow

class ProductListRepositoryImpl(
    private val productListRemoteDataSource: ProductListRemoteDataSource,
    private val productListLocalDataSource: ProductListLocalDataSource
) : ProductListRepository {

    override suspend fun getProductList(url: String, page: Int): Flow<Response<List<Product>>> =
        singleSourceOfTruth(
            getLocalData = { getProductListFromLocal() },
            getRemoteData = {
                productListRemoteDataSource.getProductListFromRemote().map { productDTO -> productDTO.asDomainModel()  }
            },
            saveDataToLocal = { productList ->
                productListLocalDataSource.deleteProductListFromDB()
                productListLocalDataSource.insertProductListToDB(productList)
            }
        )

    private suspend fun getProductListFromLocal(): List<Product> {
        var movieList: List<Product> = emptyList()

            movieList = productListLocalDataSource.getProductListFromLocal()

        return movieList
    }
}