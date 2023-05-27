package com.greenrobotdev.onlinestore.di

import ProductListLocalDataSourceImpl
import com.greenrobotdev.onlinestore.data.repository.ProductListRepositoryImpl
import com.greenrobotdev.onlinestore.data.repository.local.datasource.ProductListLocalDataSource
import com.greenrobotdev.onlinestore.data.repository.remote.datasource.ProductListRemoteDataSource
import com.greenrobotdev.onlinestore.data.repository.remote.datasourceimpl.ProductListRemoteDataSourceImpl
import com.greenrobotdev.onlinestore.domain.repository.ProductListRepository
import com.greenrobotdev.onlinestore.domain.usecases.ProductListUseCase
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        dataSourceModule,
        platformModule()
    )
}


val dataSourceModule = module {
    single<ProductListRemoteDataSource> { ProductListRemoteDataSourceImpl(get()) }
    single<ProductListLocalDataSource> { ProductListLocalDataSourceImpl(get()) }
    single<ProductListRepository> { ProductListRepositoryImpl(get(), get()) }
}

val useCasesModule: Module = module {
    factory { ProductListUseCase(get()) }
}
