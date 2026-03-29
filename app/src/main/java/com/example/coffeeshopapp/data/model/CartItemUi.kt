package com.example.coffeeshopapp.data.model

data class CartItemUi(
    val productId: Int,
    val name: String,
    val image: String,
    val price: Double,
    val quantity: Int,
    val total: Double
)
