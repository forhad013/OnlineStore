package com.greenrobotdev.onlinestore.screen.productDetails

import app.cash.molecule.RecompositionClock.Immediate
import app.cash.molecule.moleculeFlow
import com.greenrobotdev.onlinestore.data.cartStore
import com.greenrobotdev.onlinestore.data.favoriteStore
import com.greenrobotdev.onlinestore.domain.entity.Product
import com.greenrobotdev.onlinestore.navigation.ViewModel
import io.github.xxfast.decompose.router.SavedStateHandle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class ProductDetailsViewModel(
  savedState: SavedStateHandle,
  product : Product? = null
) : ViewModel(), KoinComponent {
  private val eventsFlow: MutableSharedFlow<ProductDetailsEvent> = MutableSharedFlow(5)
  private val initialState: ProductDetailsState = savedState.get() ?: ProductDetailsState(product, numberOfProduct = 0)

  val states by lazy {
    moleculeFlow(Immediate) {
      ProductDetailsUseCase(initialState, favoriteStore, cartStore,eventsFlow)
    }
      .onEach { state -> savedState.set(state) }
      .stateIn(this, SharingStarted.Lazily, initialState)
  }

  fun onFavoriteButtonPressed(){
    launch { eventsFlow.emit(ProductDetailsEvent.OnFavoritePressed) }
  }

  fun onAddToCartPressed(){
    launch { eventsFlow.emit(ProductDetailsEvent.OnAddToCartPressed) }
  }

  fun onIncreaseCountPressed(){
    launch { eventsFlow.emit(ProductDetailsEvent.OnIncreaseCountPressed) }
  }
  fun onDecreaseCountPressed(){
    launch { eventsFlow.emit(ProductDetailsEvent.OnDecreaseCountPressed) }
  }

}
