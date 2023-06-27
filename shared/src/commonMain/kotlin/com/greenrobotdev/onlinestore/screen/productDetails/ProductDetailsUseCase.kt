package com.greenrobotdev.onlinestore.screen.productDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.greenrobotdev.onlinestore.data.model.CartProducts
import com.greenrobotdev.onlinestore.data.model.SavedProducts
import com.greenrobotdev.onlinestore.domain.entity.CartItem
import com.greenrobotdev.onlinestore.domain.entity.Product
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun ProductDetailsUseCase(
    initialState: ProductDetailsState,
    favoriteStore: KStore<SavedProducts>,
    cartStore: KStore<CartProducts>,
    events: Flow<ProductDetailsEvent>,
): ProductDetailsState {

    val state by remember { mutableStateOf(initialState) }
    val isSaved: Boolean? by favoriteStore.updates
        .map { products -> products?.any { product -> product.id == initialState.product?.id } }
        .collectAsState(DontKnowYet)

    val currentProductCartCount: Int? by cartStore.updates
        .map { cartItems ->
          val product =  cartItems?.find { cartItem -> cartItem.product.id == initialState.product?.id }
            product?.quantity ?: 0
        }
        .collectAsState(DontKnowYet)

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is ProductDetailsEvent.OnFavoritePressed -> launch(Dispatchers.Unconfined) {
                    favoriteStore.update { products ->
                        val currentProduct: Product? = initialState.product
                        when {
                            currentProduct != null && isSaved == false -> products?.plus(currentProduct)

                            currentProduct != null && isSaved == true -> products?.minus(currentProduct)

                            else -> products
                        }
                    }
                }
                is ProductDetailsEvent.OnAddToCartPressed -> launch(Dispatchers.Unconfined) {
                    cartStore.update { cartItems ->
                        val currentProduct: Product? = initialState.product
                        when {
                            currentProduct != null && currentProductCartCount == 0 -> {
                                cartItems?.plus(CartItem(quantity = 1, product = currentProduct))
                            }

                            else -> cartItems
                        }
                    }
                }
            }
        }
    }

    return state.copy(isSaved = isSaved ?: false)
}