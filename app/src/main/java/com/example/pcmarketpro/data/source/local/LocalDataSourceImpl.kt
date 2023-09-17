package com.example.pcmarketpro.data.source.local

import com.example.pcmarketpro.data.model.Product
import com.example.pcmarketpro.domain.datasource.local.LocalDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalDataSourceImpl @Inject constructor(
    private val productFavoriteDAO: ProductFavoriteDAO,
    private val ioDispatcher: CoroutineContext
) : LocalDataSource {

    override suspend fun addToFavorites(product: Product) = withContext(ioDispatcher) {
        productFavoriteDAO.addToFavorite(product)
    }

    override suspend fun getFavorites(): List<Product>? = withContext(ioDispatcher) {
        productFavoriteDAO.getFavorites()
    }

    override suspend fun deleteFromFavorites(id: Int) = withContext(ioDispatcher) {
        productFavoriteDAO.deleteFromFavorites(id)
    }

    override suspend fun clearFavorites() = withContext(ioDispatcher) {
        productFavoriteDAO.clearFavorites()
    }

    override suspend fun getFavoritesNamesList(): List<String>? = withContext(ioDispatcher) {
        productFavoriteDAO.getFavoritesTitles()
    }

}
