package com.greenrobotdev.onlinestore.data

import com.greenrobotdev.onlinestore.data.model.CachedProducts
import com.greenrobotdev.onlinestore.data.model.SavedProducts
import com.greenrobotdev.onlinestore.di.appStorage
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf

actual val favoriteStore: KStore<SavedProducts> by lazy {
  storeOf("${appStorage}/saved.json", emptySet())
}

actual val cachedStore: KStore<CachedProducts> by lazy {
  storeOf("${appStorage}/cached.json", emptySet())
}
