package com.greenrobotdev.onlinestore.domain.entity

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class CartItem(
    val quantity: Int,
    val product: Product,
): Parcelable
