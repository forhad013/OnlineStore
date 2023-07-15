package com.greenrobotdev.onlinestore.screen.productList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.greenrobotdev.onlinestore.data.mapper.asDomainModel
import com.greenrobotdev.onlinestore.data.model.CartProducts
import com.greenrobotdev.onlinestore.data.model.SavedProducts
import com.greenrobotdev.onlinestore.data.repository.remote.datasourceimpl.ProductListRemoteDataSourceImpl
import com.greenrobotdev.onlinestore.domain.entity.Product
import com.greenrobotdev.onlinestore.screen.productDetails.DontKnowYet
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Composable
fun ProductListUseCase(
    initialState: ProductListState,
    productListRepository: ProductListRemoteDataSourceImpl,
    favoriteStore: KStore<SavedProducts>,
    cartStore: KStore<CartProducts>,
    events: Flow<ProductListEvent>,
): ProductListState {

    var products: List<Product>? by remember { mutableStateOf(initialState.products) }
    var refreshes: Int by remember { mutableStateOf(0) }

    val numberOfFav: Int? by favoriteStore.updates
        .map { it?.size }
        .collectAsState(DontKnowYet)

    val numberOfCartProducts: Int? by cartStore.updates
        .map { it?.size }
        .collectAsState(DontKnowYet)

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
    return ProductListState(products = products, numberOfFavorite = numberOfFav ?: 0, numberOfCartProducts = numberOfCartProducts ?: 0)
}