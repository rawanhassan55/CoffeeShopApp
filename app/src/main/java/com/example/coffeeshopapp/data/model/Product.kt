package com.example.coffeeshopapp.data.model

data class Product(
    val id: Int = 0,
    val name: String = "",
    val subtitle: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val category: CoffeeCategory = CoffeeCategory.ESPRESSO,
    val imageUrl: String = "",
    val isFavorite :Boolean = false
)

