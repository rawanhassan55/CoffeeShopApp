package com.example.coffeeshopapp.viewmodel.cart

import com.example.coffeeshopapp.data.model.CartItemUi

data class CartState(
    val items : List<CartItemUi> = emptyList(),
    val subtotal : Double = 0.0,
    val tax: Double = 0.0,
    val total: Double = 0.0,
    val isEmpty: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)
