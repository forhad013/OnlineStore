package com.greenrobotdev.onlinestore.data.repository.remote.datasource

import com.greenrobotdev.onlinestore.data.model.ProductDTO

interface ProductListRemoteDataSource {
    suspend fun getProductListFromRemote(): Result<List<ProductDTO>>

}