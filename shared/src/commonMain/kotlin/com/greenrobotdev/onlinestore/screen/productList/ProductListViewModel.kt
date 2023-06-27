package com.greenrobotdev.onlinestore.screen.productList

import app.cash.molecule.RecompositionClock.Immediate
import app.cash.molecule.moleculeFlow
import com.greenrobotdev.onlinestore.data.cartStore
import com.greenrobotdev.onlinestore.data.favoriteStore
import com.greenrobotdev.onlinestore.data.repository.remote.datasourceimpl.ProductListRemoteDataSourceImpl
import com.greenrobotdev.onlinestore.navigation.ViewModel
import com.greenrobotdev.onlinestore.utils.HttpClient
import io.github.xxfast.decompose.router.SavedStateHandle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class ProductListViewModel(
  savedState: SavedStateHandle
) : ViewModel(), KoinComponent {
  private val initialState: ProductListState = savedState.get() ?: ProductListState()
  private val webService = ProductListRemoteDataSourceImpl(HttpClient)
  private val eventsFlow: MutableSharedFlow<ProductListEvent> = MutableSharedFlow(5)

  val states by lazy {
    moleculeFlow(Immediate) {
      ProductListUseCase(initialState, webService, favoriteStore, cartStore, eventsFlow)
    }
      .onEach { state -> savedState.set(state) }
      .stateIn(this, SharingStarted.Lazily, initialState)
  }

  fun onRefresh() { launch { eventsFlow.emit(ProductListEvent.Refresh) } }
}
