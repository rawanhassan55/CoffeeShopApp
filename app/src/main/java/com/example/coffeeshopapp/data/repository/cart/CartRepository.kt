package com.example.coffeeshopapp.data.repository.cart

import com.example.coffeeshopapp.data.local.cart.CartEntity
import com.example.coffeeshopapp.data.model.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun observeCart(): Flow<List<CartEntity>>

    suspend fun addToCart(product: Product, quantity: Int)

    suspend fun increaseQty(productId: Int)

    suspend fun decreaseQty(productId: Int)

    suspend fun remove(productId: Int)

    suspend fun clearCart()
}
