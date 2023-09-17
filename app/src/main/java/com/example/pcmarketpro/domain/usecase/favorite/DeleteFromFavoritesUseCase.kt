package com.example.pcmarketpro.domain.usecase.favorite

import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(id: Int) = productsRepository.deleteFromFavorites(id)
}