package com.greenrobotdev.onlinestore.screen.productList

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.greenrobotdev.onlinestore.domain.entity.Product

val Loading: Nothing? = null

@Parcelize
data class ProductListState(
  val products: List<Product>? = Loading
) : Parcelable

sealed interface ProductListEvent {
  object Refresh: ProductListEvent
  data class SelectSection(val section: Product): ProductListEvent
}
