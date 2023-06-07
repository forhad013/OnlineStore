package com.greenrobotdev.onlinestore.screen.productList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.greenrobotdev.onlinestore.data.base.Response
import com.greenrobotdev.onlinestore.data.mapper.asDomainModel
import com.greenrobotdev.onlinestore.data.model.ProductDTO
import com.greenrobotdev.onlinestore.data.repository.ProductListRepositoryImpl
import com.greenrobotdev.onlinestore.data.repository.remote.datasourceimpl.ProductListRemoteDataSourceImpl
import com.greenrobotdev.onlinestore.domain.repository.ProductListRepository

@Composable
fun ProductListUseCase(
    initialState: ProductListState,
    productListRepository: ProductListRemoteDataSourceImpl
): ProductListState {

    var state by remember { mutableStateOf(initialState) }

    var refreshes: Int by remember { mutableStateOf(0) }

    LaunchedEffect(refreshes) {
        // Don't autoload the stories when restored from process death
        if (refreshes == 0 && state != ProductListState()) return@LaunchedEffect

      val result =  productListRepository.getProductListFromRemote().getOrNull() ?: return@LaunchedEffect

        state = ProductListState(
            products = result.map { it.asDomainModel() }
        )
    }
    return state
}