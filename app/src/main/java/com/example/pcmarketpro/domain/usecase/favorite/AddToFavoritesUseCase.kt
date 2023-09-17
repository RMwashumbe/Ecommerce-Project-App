package com.example.pcmarketpro.domain.usecase.favorite

import com.example.pcmarketpro.data.model.Product
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(product: Product) = productsRepository.addToFavorites(product)
}