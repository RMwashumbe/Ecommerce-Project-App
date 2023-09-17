package com.example.pcmarketpro.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.data.model.Product
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val addToBagUseCase: AddToBagUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _crudResponse = MutableLiveData<Resource<CRUDResponse>>()
    val crudResponse: LiveData<Resource<CRUDResponse>> = _crudResponse

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val productModel = savedStateHandle.get<Product>("product")

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    init {
        getProduct()
        _isFavorite.value = productModel?.isFavorite
    }

    fun addToBag() {
        viewModelScope.launch {
            productModel?.let {
                _crudResponse.value = Resource.Loading
                _crudResponse.value = addToBagUseCase(it)
            }
        }
    }

    private fun addToFavorite(product: Product) {
        viewModelScope.launch {
            addToFavoritesUseCase(product)
        }
    }

    private fun deleteFromFavorites(id: Int) {
        viewModelScope.launch {
            deleteFromFavoritesUseCase(id)
        }
    }

    private fun getProduct() {
        productModel?.let {
            _product.value = it
        }
    }

    fun setFavoriteState() {
        productModel?.let {
            if (_isFavorite.value == true) {
                deleteFromFavorites(it.id)
                _isFavorite.value = false
            } else {
                addToFavorite(it)
                _isFavorite.value = true
            }
        }
    }
}