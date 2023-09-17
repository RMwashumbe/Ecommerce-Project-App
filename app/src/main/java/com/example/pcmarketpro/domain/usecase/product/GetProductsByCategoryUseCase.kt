package com.example.pcmarketpro.domain.usecase.product

import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.Product
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductsByCategoryUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(category: String): Resource<List<Product>> {
        return try {
            Resource.Loading
            Resource.Success(productsRepository.getProductsByCategory(category))
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}