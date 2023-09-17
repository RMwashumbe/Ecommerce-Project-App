package com.example.pcmarketpro.domain.usecase.bag

import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.CRUDResponse
import com.example.pcmarketpro.data.model.Product
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddToBagUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(product: Product): Resource<CRUDResponse> {
        return try {
            Resource.Loading
            Resource.Success(productsRepository.addToBag(product))
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}