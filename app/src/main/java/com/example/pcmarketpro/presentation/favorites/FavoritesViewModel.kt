package com.example.pcmarketpro.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.Product
import com.example.pcmarketpro.domain.usecase.favorite.DeleteFromFavoritesUseCase
import com.example.pcmarketpro.domain.usecase.favorite.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase
) : ViewModel() {

    private val _favoriteList = MutableLiveData<Resource<List<Product>>>()
    val favoriteList: LiveData<Resource<List<Product>>> = _favoriteList

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            _favoriteList.value = getFavoritesUseCase()
        }
    }

    fun deleteFromFavorites(id: Int) {
        viewModelScope.launch {
            deleteFromFavoritesUseCase(id)
            getFavorites()
        }
    }
}