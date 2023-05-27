package com.greenrobotdev.onlinestore.domain.entity

import com.arkivanov.essenty.parcelable.Parcelize

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val image : String,
    val description: String,
    val category: String,
    val rate: Double,
    val count: Double
)
