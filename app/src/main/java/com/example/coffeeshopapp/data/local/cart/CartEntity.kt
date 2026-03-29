package com.example.coffeeshopapp.data.local.cart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity (
    @PrimaryKey
    val productId: Int = 0,
    val name: String,
    val priceAtAddTime : Double, // snapshot(server return final price at checkout)
    val imageUrl : String,
    val quantity : Int
    )
