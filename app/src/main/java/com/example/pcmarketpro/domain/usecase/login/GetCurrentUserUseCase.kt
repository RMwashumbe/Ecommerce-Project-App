package com.example.pcmarketpro.domain.usecase.login

import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.User
import com.example.pcmarketpro.domain.repository.Authenticator
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    suspend operator fun invoke(): Resource<User> {

        return try {
            Resource.Loading
            Resource.Success(authenticator.getCurrentUser())
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}