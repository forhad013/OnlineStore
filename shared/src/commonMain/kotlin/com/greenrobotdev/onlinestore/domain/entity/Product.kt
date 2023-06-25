package com.greenrobotdev.onlinestore.domain.entity

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val image : String,
    val description: String,
    val category: String,
    val rate: Double,
    val count: Double
): Parcelable
