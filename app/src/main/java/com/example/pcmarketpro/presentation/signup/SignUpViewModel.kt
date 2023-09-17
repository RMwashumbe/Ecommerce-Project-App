package com.example.pcmarketpro.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val checkCurrentUserUseCase: CheckCurrentUserUseCase
) : ViewModel() {

    private val _result = MutableLiveData<Resource<Unit>>()
    val result: LiveData<Resource<Unit>> = _result

    private val _checkCurrentUser = MutableLiveData<Boolean>()
    val checkCurrentUser: LiveData<Boolean> = _checkCurrentUser

    init {
        checkCurrentUser()
    }

    fun signUpWithEmailAndPassword(user: User, password: String) {
        viewModelScope.launch {
            _result.value = Resource.Loading
            _result.value = signUpUseCase(user, password)
        }
    }

    private fun checkCurrentUser() {
        viewModelScope.launch {
            _checkCurrentUser.value = checkCurrentUserUseCase()
        }
    }
}