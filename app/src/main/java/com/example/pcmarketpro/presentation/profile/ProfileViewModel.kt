package com.example.pcmarketpro.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.User
import com.example.pcmarketpro.domain.usecase.login.GetCurrentUserUseCase
import com.example.pcmarketpro.domain.usecase.login.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _currentUser = MutableLiveData<Resource<User>>(Resource.Loading)
    val currentUser: LiveData<Resource<User>> = _currentUser

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase()
        }
    }
}