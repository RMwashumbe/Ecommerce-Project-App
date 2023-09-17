package com.example.pcmarketpro.presentation.paymentsuccess

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.CRUDResponse
import com.example.pcmarketpro.domain.usecase.bag.ClearBagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentSuccessViewModel @Inject constructor(
    private val clearBagUseCase: ClearBagUseCase
) : ViewModel() {

    private val _result = MutableLiveData<Resource<CRUDResponse>>()
    val result: LiveData<Resource<CRUDResponse>> = _result

    fun clearBag() {
        viewModelScope.launch {
            _result.value = Resource.Loading
            _result.value = clearBagUseCase()
        }
    }
}