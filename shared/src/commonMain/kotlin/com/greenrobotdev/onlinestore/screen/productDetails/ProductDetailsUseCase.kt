package com.greenrobotdev.onlinestore.screen.productDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.greenrobotdev.onlinestore.data.model.SavedProducts
import com.greenrobotdev.onlinestore.domain.entity.Product
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun ProductDetailsUseCase(
    initialState: ProductDetailsState,
    store: KStore<SavedProducts>,
    events: Flow<ProductDetailsEvent>,
): ProductDetailsState {

    val state by remember { mutableStateOf(initialState) }
    val isSaved: Boolean? by store.updates
        .map { products -> products?.any { product -> product.id == initialState.product?.id } }
        .collectAsState(DontKnowYet)

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is ProductDetailsEvent.OnFavoriteButtonPressed -> launch(Dispatchers.Unconfined) {
                    store.update { products ->
                        val productToSaved: Product? = initialState.product
                        when {
                            productToSaved != null && isSaved == false -> products?.plus(productToSaved)

                            productToSaved != null && isSaved == true -> products?.minus(productToSaved)

                            else -> products
                        }
                    }
                }
            }
        }
    }

    return state.copy(isSaved = isSaved ?: false)
}