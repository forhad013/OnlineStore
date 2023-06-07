package com.greenrobotdev.onlinestore.screen.productDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun ProductListUseCase(
    initialState: ProductDetailsState,
): ProductDetailsState {

    val state by remember { mutableStateOf(initialState) }

    return state
}