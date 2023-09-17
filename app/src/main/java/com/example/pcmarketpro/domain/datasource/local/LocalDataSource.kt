package com.example.pcmarketpro.domain.datasource.local

import com.example.pcmarketpro.data.model.Product

interface LocalDataSource {
    suspend fun addToFavorites(product: Product)

    suspend fun getFavorites(): List<Product>?

    suspend fun deleteFromFavorites(id: Int)

    suspend fun clearFavorites()

    suspend fun getFavoritesNamesList(): List<String>?

}
