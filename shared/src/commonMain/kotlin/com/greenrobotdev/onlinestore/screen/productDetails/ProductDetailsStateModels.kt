package com.greenrobotdev.onlinestore.screen.productDetails

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.greenrobotdev.onlinestore.domain.entity.Product

val Loading: Nothing? = null
val DontKnowYet: Nothing? = null

@Parcelize
data class ProductDetailsState(
  val product: Product? = Loading,
  val isSaved: Boolean = false
) : Parcelable


sealed interface ProductDetailsEvent {
  object OnFavoritePressed: ProductDetailsEvent
  object OnAddToCartPressed: ProductDetailsEvent
}


