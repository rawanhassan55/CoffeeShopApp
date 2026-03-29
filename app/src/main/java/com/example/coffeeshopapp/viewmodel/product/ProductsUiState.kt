package com.example.coffeeshopapp.viewmodel.product

import com.example.coffeeshopapp.data.model.Product

sealed class ProductsUiState {

    data object Loading : ProductsUiState()

    data class Success(val product: List<Product>) : ProductsUiState()

    // // Error state for displaying on-screen (TextView or empty list)
    data class Error(val message: String) : ProductsUiState()
}