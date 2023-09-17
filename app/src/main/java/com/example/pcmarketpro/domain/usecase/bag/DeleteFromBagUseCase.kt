package com.example.pcmarketpro.domain.usecase.bag

import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.CRUDResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteFromBagUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke(id: Int): Resource<CRUDResponse> {
        return try {
            Resource.Loading
            Resource.Success(productsRepository.deleteFromBag(id))
        } catch (e: HttpException) {
            Resource.Error(e)
        } catch (e: IOException) {
            Resource.Error(e)
        }
    }
}
