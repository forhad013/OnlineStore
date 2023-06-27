package com.greenrobotdev.onlinestore.screen.productList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.greenrobotdev.onlinestore.domain.entity.Product

@Composable
actual fun ProductList(
    products: List<Product>,
    onRefresh: () -> Unit,
    isRefreshing : Boolean,
    onProductSelect: (product: Product) -> Unit,
    block: @Composable (products: Product) -> Unit,
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(products) { product -> block(product) }
    }
}
