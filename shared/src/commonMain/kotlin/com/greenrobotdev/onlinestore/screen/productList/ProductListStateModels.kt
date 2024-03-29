package com.greenrobotdev.onlinestore.screen.productList

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.greenrobotdev.onlinestore.domain.entity.Product

val Loading: Nothing? = null

@Parcelize
data class ProductListState(
  val products: List<Product>? = Loading,
  val numberOfFavorite : Int = 0,
  val numberOfCartProducts : Int = 0,
  val isRefreshing  : Boolean = false
) : Parcelable

sealed interface ProductListEvent {
  object Refresh: ProductListEvent
}
