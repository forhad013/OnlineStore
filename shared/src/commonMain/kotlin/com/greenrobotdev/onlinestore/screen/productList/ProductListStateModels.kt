package com.greenrobotdev.onlinestore.screen.productList

import com.greenrobotdev.onlinestore.domain.entity.Product

val Loading: Nothing? = null



sealed class ProductListState{

  object InProgress: ProductListState()

  data class Idle(
    val products: List<Product>?
  ) : ProductListState()
}

sealed interface ProductListEvent {
  object Refresh: ProductListEvent
  data class SelectSection(val section: Product): ProductListEvent
}
