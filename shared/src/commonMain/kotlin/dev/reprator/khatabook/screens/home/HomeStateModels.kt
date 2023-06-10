package dev.reprator.khatabook.screens.home

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed class StoryHomeScreen: Parcelable {
  object List: StoryHomeScreen()
}
