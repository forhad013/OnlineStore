package com.greenrobotdev.onlinestore.data.repository.remote.datasourceimpl

import com.greenrobotdev.onlinestore.data.model.ProductDTO
import com.greenrobotdev.onlinestore.data.repository.remote.datasource.ProductListRemoteDataSource
import com.greenrobotdev.onlinestore.utils.get
import io.ktor.client.HttpClient
import io.ktor.http.path

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
