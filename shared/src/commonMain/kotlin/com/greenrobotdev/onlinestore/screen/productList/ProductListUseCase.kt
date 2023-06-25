package com.greenrobotdev.onlinestore.screen.productList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.greenrobotdev.onlinestore.data.mapper.asDomainModel
import com.greenrobotdev.onlinestore.data.repository.remote.datasourceimpl.ProductListRemoteDataSourceImpl
import com.greenrobotdev.onlinestore.domain.entity.Product
import kotlinx.coroutines.flow.Flow

@Composable
fun ProductListUseCase(
    initialState: ProductListState,
    productListRepository: ProductListRemoteDataSourceImpl,
    events: Flow<ProductListEvent>,
): ProductListState {

    var products: List<Product>? by remember { mutableStateOf(initialState.products) }
    var refreshes: Int by remember { mutableStateOf(0) }

    LaunchedEffect(refreshes) {

        // Don't autoload the stories when restored from process death
        if (refreshes == 0 && products != Loading) return@LaunchedEffect
        products = Loading

        val result = productListRepository.getProductListFromRemote().getOrNull() ?: return@LaunchedEffect

        products = result.map { it.asDomainModel() }
    }

    LaunchedEffect(Unit) {
        events.collect { event ->
            println(event.toString())
            when (event) {
                ProductListEvent.Refresh -> refreshes++
            }
        }
    }
    return ProductListState(products = products)
}