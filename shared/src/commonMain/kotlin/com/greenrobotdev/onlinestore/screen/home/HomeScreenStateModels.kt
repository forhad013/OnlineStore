package com.greenrobotdev.onlinestore.screen.home

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed class StoryHomeScreen: Parcelable {
  object List: StoryHomeScreen()

   object Details: StoryHomeScreen()
}
