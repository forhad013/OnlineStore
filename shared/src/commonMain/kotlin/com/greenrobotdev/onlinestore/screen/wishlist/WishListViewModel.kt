package com.greenrobotdev.onlinestore.screen.wishlist

import app.cash.molecule.RecompositionClock.Immediate
import app.cash.molecule.moleculeFlow
import com.greenrobotdev.onlinestore.data.favoriteStore
import com.greenrobotdev.onlinestore.navigation.ViewModel
import io.github.xxfast.decompose.router.SavedStateHandle
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent

class WishListViewModel(
  savedState: SavedStateHandle
) : ViewModel(), KoinComponent {
  private val initialState: WishListState = savedState.get() ?: WishListState()

  val states by lazy {
    moleculeFlow(Immediate) {
      WishListUseCase(initialState, favoriteStore)
    }
      .onEach { state -> savedState.set(state) }
      .stateIn(this, SharingStarted.Lazily, initialState)
  }

}
