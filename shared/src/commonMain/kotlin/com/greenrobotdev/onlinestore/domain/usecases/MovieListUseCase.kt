package com.greenrobotdev.onlinestore.domain.usecases

import com.greenrobotdev.onlinestore.data.base.Response
import com.greenrobotdev.onlinestore.domain.entity.Product
import com.greenrobotdev.onlinestore.domain.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow

class ProductListUseCase(private val movieListRepository: ProductListRepository) {

    suspend fun getPopularMovieList(url: String, page: Int): Flow<Response<List<Product>>> {
        return movieListRepository.getProductList(url, page)
    }
}