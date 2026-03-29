package com.example.coffeeshopapp.viewmodel.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshopapp.data.mapper.toProduct
import com.example.coffeeshopapp.data.model.Product
import com.example.coffeeshopapp.data.repository.firebase.firestore.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

    // to show all favorites StateFlow
    val favoriteProducts: StateFlow<List<Product>> = repository.observeFavorites()
        .map { list -> list.map { it.toProduct() } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //Refresh / Sync favorites
    fun syncFavorites() {
        viewModelScope.launch {
            repository.syncFavorites()
        }
    }

    // Toggle favorite (add/remove)
    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            if (product.isFavorite) {
                repository.removeFavorite(product.id)
            } else {
                repository.addFavorite(product.id)
            }
            //syncFavorites()
        }

    }
}