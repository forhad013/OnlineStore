package com.greenrobotdev.onlinestore.data.repository.local.datasource

import com.greenrobotdev.onlinestore.entity.Product

interface ProductListLocalDataSource {
    fun getProductListFromLocal(): List<Product>
}