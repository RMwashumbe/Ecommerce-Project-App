package com.example.pcmarketpro.domain.usecase.login

import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.domain.repository.Authenticator
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    suspend operator fun invoke(email: String): Resource<Void> {

        return try {
            Resource.Loading
            Resource.Success(authenticator.sendPasswordResetEmail(email))
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}