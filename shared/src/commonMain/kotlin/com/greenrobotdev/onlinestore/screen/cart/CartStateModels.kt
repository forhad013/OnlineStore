package com.greenrobotdev.onlinestore.screen.cart

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.greenrobotdev.onlinestore.domain.entity.CartItem
import com.greenrobotdev.onlinestore.domain.entity.Product

val nothing: Nothing? = null

@Parcelize
data class CartState(
  val products: List<CartItem>? = nothing,
) : Parcelable

sealed interface CartEvent {
  data class onSelect(val product : Product): CartEvent
}
