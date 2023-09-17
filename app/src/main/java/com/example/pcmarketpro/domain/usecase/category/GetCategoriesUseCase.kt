package com.example.pcmarketpro.domain.usecase.category

import com.example.pcmarketpro.common.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke(): Resource<List<String>> {
        return try {
            Resource.Loading
            Resource.Success(productsRepository.getCategories())
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}