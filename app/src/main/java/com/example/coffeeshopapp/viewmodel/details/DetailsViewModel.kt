package com.example.coffeeshopapp.viewmodel.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshopapp.data.mapper.toProduct
import com.example.coffeeshopapp.data.model.Product
import com.example.coffeeshopapp.data.repository.firebase.firestore.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository : ProductsRepository,
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product : StateFlow<Product?> = _product.asStateFlow()

    fun loadProducts(id: Int){
        viewModelScope.launch {
            val entity = repository.getProductById(id)
            _product.value = entity?.toProduct()
        }
    }

    // Toggle favorite (add/remove)
    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val newState = !product.isFavorite

            if (product.isFavorite) {
                repository.removeFavorite(product.id)
            } else {
                repository.addFavorite(product.id)
            }

            _product.value = product.copy(isFavorite = newState)
        }
    }

}
