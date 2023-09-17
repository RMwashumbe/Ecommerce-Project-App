package com.example.pcmarketpro.domain.usecase.login

import com.example.pcmarketpro.domain.repository.Authenticator
import javax.inject.Inject

class CheckCurrentUserUseCase @Inject constructor(
    private val authenticator: Authenticator
) {
    suspend operator fun invoke(): Boolean = authenticator.isCurrentUserExist()
}