package com.greenrobotdev.onlinestore.data.repository.local.datasource

import com.greenrobotdev.onlinestore.domain.entity.Product

interface ProductListLocalDataSource {
    fun getProductListFromLocal(): List<Product>

    fun insertProductListToDB(products: List<Product>)

    fun deleteProductListFromDB()
}