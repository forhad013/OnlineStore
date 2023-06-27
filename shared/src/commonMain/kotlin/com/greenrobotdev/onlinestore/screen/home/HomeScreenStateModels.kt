package com.greenrobotdev.onlinestore.screen.home

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.greenrobotdev.onlinestore.domain.entity.Product

@Parcelize
sealed class StoryHomeScreen: Parcelable {
  object List: StoryHomeScreen()

  data class   Details(val product: Product): StoryHomeScreen()

  object Wishlist: StoryHomeScreen()
  object Cart: StoryHomeScreen()
}
