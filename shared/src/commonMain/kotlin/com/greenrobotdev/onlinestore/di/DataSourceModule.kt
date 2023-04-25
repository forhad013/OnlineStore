package com.greenrobotdev.onlinestore.di

import com.greenrobotdev.onlinestore.data.repository.remote.datasource.ProductListRemoteDataSource
import com.greenrobotdev.onlinestore.data.repository.remote.datasourceimpl.ProductListRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<ProductListRemoteDataSource> { ProductListRemoteDataSourceImpl(get()) }
 }