package com.example.pcmarketpro.domain.usecase.product

import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.Product
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(word: String): Resource<List<Product>> {
        return try {
            Resource.Loading
            Resource.Success(productsRepository.searchProduct(word))
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}