package com.example.coffeeshopapp.data.local.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coffeeshopapp.data.model.CoffeeCategory

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val subTitle: String,
    val description: String,
    val imageUrl: String,
    val price: Double,
    val rating: Double,
    val category: CoffeeCategory = CoffeeCategory.ESPRESSO,
    val isFavorite: Boolean = false
)
