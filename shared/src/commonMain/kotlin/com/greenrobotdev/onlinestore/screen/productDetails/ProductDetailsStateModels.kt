package com.greenrobotdev.onlinestore.screen.productDetails

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.greenrobotdev.onlinestore.domain.entity.Product

val Loading: Nothing? = null

@Parcelize
data class ProductDetailsState(
  val product: Product? = Loading
) : Parcelable

