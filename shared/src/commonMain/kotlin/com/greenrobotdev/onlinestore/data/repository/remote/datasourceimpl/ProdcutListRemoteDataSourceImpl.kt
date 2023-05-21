package com.greenrobotdev.onlinestore.data.repository.remote.datasourceimpl

import com.greenrobotdev.onlinestore.data.model.ProductDTO
import com.greenrobotdev.onlinestore.data.repository.remote.datasource.ProductListRemoteDataSource
import com.greenrobotdev.onlinestore.data.util.APIConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ProductListRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : ProductListRemoteDataSource {

    override suspend fun getProductListFromRemote(): List<ProductDTO> {
        return httpClient.get(urlString = "${APIConstants.BASE_URL}/products") {
            contentType(ContentType.Application.Json)
        }.body()
    }
}
