package com.example.pcmarketpro.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.Product
import com.example.pcmarketpro.data.model.User
import com.example.pcmarketpro.domain.usecase.bag.GetBagProductsCountUseCase
import com.example.pcmarketpro.domain.usecase.category.GetCategoriesUseCase
import com.example.pcmarketpro.domain.usecase.favorite.AddToFavoritesUseCase
import com.example.pcmarketpro.domain.usecase.favorite.DeleteFromFavoritesUseCase
import com.example.pcmarketpro.domain.usecase.login.GetCurrentUserUseCase
import com.example.pcmarketpro.domain.usecase.product.GetSaleProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSaleProductsUseCase: GetSaleProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getBagProductsCountUseCase: GetBagProductsCountUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _user = MutableLiveData<Resource<User>?>(Resource.Loading)
    val user: LiveData<Resource<User>> = _user

    private val _saleProducts = MutableLiveData<Resource<List<Product>>>(Resource.Loading)
    val saleProducts: LiveData<Resource<List<Product>>> = _saleProducts

    private val _bagProductsCount = MutableLiveData<Resource<Int>>(Resource.Loading)
    val bagProductsCount: LiveData<Resource<Int>> = _bagProductsCount

    private val _categories = MutableLiveData<Resource<List<String>>>(Resource.Loading)
    val categories: LiveData<Resource<List<String>>> = _categories

    init {
        viewModelScope.launch {
            _user.value = getCurrentUserUseCase()
            _saleProducts.value = getSaleProductsUseCase()
            _bagProductsCount.value = getBagProductsCountUseCase()
            _categories.value = getCategoriesUseCase()
        }
    }

    fun addToFavorite(product: Product) {
        viewModelScope.launch {
            addToFavoritesUseCase(product)
        }
    }

    fun deleteFromFavorites(id: Int) {
        viewModelScope.launch {
            deleteFromFavoritesUseCase(id)
        }
    }
}