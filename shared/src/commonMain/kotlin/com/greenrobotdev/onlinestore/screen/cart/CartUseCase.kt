package com.greenrobotdev.onlinestore.screen.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.greenrobotdev.onlinestore.data.model.CartProducts
import com.greenrobotdev.onlinestore.domain.entity.CartItem
import com.greenrobotdev.onlinestore.screen.productDetails.DontKnowYet
import io.github.xxfast.kstore.KStore
import kotlinx.coroutines.flow.map

@Composable
fun CartUseCase(
    initialState: CartState,
    store: KStore<CartProducts>
): CartState {
    val state: CartState by remember { mutableStateOf(initialState) }
    
    val products: List<CartItem>? by store.updates.map {
        it?.toList() ?: nothing
    }.collectAsState(DontKnowYet)

    return state.copy(products = products)
}