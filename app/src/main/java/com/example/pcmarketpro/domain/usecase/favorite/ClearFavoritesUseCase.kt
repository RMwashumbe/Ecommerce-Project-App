package com.example.pcmarketpro.domain.usecase.favorite

import javax.inject.Inject

class ClearFavoritesUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke() = productsRepository.clearFavorites()
}