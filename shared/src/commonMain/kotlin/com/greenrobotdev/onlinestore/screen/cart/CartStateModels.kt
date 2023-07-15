package com.greenrobotdev.onlinestore.screen.cart

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.greenrobotdev.onlinestore.domain.entity.CartItem

val nothing: Nothing? = null

@Parcelize
data class CartState(
  val cartItems: List<CartItem>? = nothing,
  val selectedCarts: List<CartItem> = emptyList(),
) : Parcelable

sealed interface CartEvent {
  data class OnSelect(val cartItem: CartItem, val isSelected: Boolean) : CartEvent
  data class OnIncreaseQt(val cartItem: CartItem) : CartEvent
  data class OnDecreaseQt(val cartItem: CartItem) : CartEvent
  data class OnRemove(val cartItem: CartItem) : CartEvent
}
