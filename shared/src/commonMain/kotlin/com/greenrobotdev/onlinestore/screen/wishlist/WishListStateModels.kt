package com.greenrobotdev.onlinestore.screen.wishlist

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.greenrobotdev.onlinestore.domain.entity.Product

val nothing: Nothing? = null

@Parcelize
data class WishListState(
  val products: List<Product>? = nothing,
) : Parcelable

sealed interface WishListEvent {
  data class onSelect(val product : Product): WishListEvent
}
