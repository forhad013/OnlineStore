package com.greenrobotdev.onlinestore.data.repository.remote.datasourceimpl

import com.greenrobotdev.onlinestore.data.model.ProductDTO
import com.greenrobotdev.onlinestore.data.repository.remote.datasource.ProductListRemoteDataSource
import com.greenrobotdev.onlinestore.data.util.APIConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import com.greenrobotdev.onlinestore.data.util.get

class ProductListRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : ProductListRemoteDataSource {

    override suspend fun getProductListFromRemote(): Result<List<ProductDTO>>  = httpClient
        .get { url { path("products") } }


//    override suspend fun getProductListFromRemote(): Result<List<ProductDTO>> {
//        return httpClient.get(urlString = "products") {
//            contentType(ContentType.Application.Json)
//        }.body()
//    }
}
