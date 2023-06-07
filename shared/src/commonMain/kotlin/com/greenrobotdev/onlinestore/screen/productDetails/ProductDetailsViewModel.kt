package com.greenrobotdev.onlinestore.screen.productDetails

import app.cash.molecule.RecompositionClock.Immediate
import app.cash.molecule.moleculeFlow
import com.greenrobotdev.onlinestore.domain.entity.Product
import com.greenrobotdev.onlinestore.navigation.ViewModel
import io.github.xxfast.decompose.router.SavedStateHandle
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent

class ProductDetailsViewModel(
  savedState: SavedStateHandle,
  product : Product? = null
) : ViewModel(), KoinComponent {
  private val initialState: ProductDetailsState = savedState.get() ?: ProductDetailsState(product)



  val states by lazy {
    moleculeFlow(Immediate) {
      ProductListUseCase(initialState)
    }
      .onEach { state -> savedState.set(state) }
      .stateIn(this, SharingStarted.Lazily, initialState)
  }

}