package com.greenrobotdev.onlinestore.screen.cart

import app.cash.molecule.RecompositionClock.Immediate
import app.cash.molecule.moleculeFlow
import com.greenrobotdev.onlinestore.data.cartStore
import com.greenrobotdev.onlinestore.navigation.ViewModel
import io.github.xxfast.decompose.router.SavedStateHandle
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent

class CartViewModel(
  savedState: SavedStateHandle
) : ViewModel(), KoinComponent {
  private val initialState: CartState = savedState.get() ?: CartState()

  val states by lazy {
    moleculeFlow(Immediate) {
      CartUseCase(initialState, cartStore)
    }
      .onEach { state -> savedState.set(state) }
      .stateIn(this, SharingStarted.Lazily, initialState)
  }

}
