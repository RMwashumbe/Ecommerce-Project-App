package com.example.pcmarketpro.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.Product
import com.example.pcmarketpro.domain.usecase.product.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductUseCase: SearchProductUseCase,
) : ViewModel() {

    private val _products = MutableLiveData<Resource<List<Product>>>()
    val products: LiveData<Resource<List<Product>>> = _products

    fun searchProduct(query: String) {
        viewModelScope.launch {
            _products.value = Resource.Loading
            _products.value = searchProductUseCase(query)
        }
    }
}