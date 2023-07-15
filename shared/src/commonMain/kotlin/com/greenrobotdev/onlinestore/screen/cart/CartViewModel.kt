package com.greenrobotdev.onlinestore.screen.cart

import app.cash.molecule.RecompositionClock.Immediate
import app.cash.molecule.moleculeFlow
import com.greenrobotdev.onlinestore.data.cartStore
import com.greenrobotdev.onlinestore.domain.entity.CartItem
import com.greenrobotdev.onlinestore.navigation.ViewModel
import io.github.xxfast.decompose.router.SavedStateHandle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CartViewModel(
  savedState: SavedStateHandle
) : ViewModel(), KoinComponent {
  private val initialState: CartState = savedState.get() ?: CartState()
  private val eventsFlow: MutableSharedFlow<CartEvent> = MutableSharedFlow(5)

  val states by lazy {
    moleculeFlow(Immediate) {
      CartUseCase(initialState, cartStore, eventsFlow)
    }
      .onEach { state -> savedState.set(state) }
      .stateIn(this, SharingStarted.Eagerly, initialState)
  }

  fun onRemoveProduct(cartItem: CartItem){
    launch { eventsFlow.emit(CartEvent.OnRemove(cartItem)) }
  }

  fun onSelectProduct(cartItem: CartItem, isSelected : Boolean){
    launch { eventsFlow.emit(CartEvent.OnSelect(cartItem,isSelected)) }
  }

  fun onAddQuantity(cartItem: CartItem){
    launch { eventsFlow.emit(CartEvent.OnIncreaseQt(cartItem)) }
  }

  fun onDecreaseQuantity(cartItem: CartItem){
    launch { eventsFlow.emit(CartEvent.OnDecreaseQt(cartItem)) }
  }

}
