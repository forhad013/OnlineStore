package com.greenrobotdev.onlinestore.screen.productList

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.greenrobotdev.onlinestore.entity.Product

val Loading: Nothing? = null


@Parcelize
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
