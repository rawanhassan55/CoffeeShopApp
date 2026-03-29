package com.example.coffeeshopapp.data.repository.firebase.firestore

import com.example.coffeeshopapp.data.local.product.ProductEntity
import com.example.coffeeshopapp.utils.result.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun observeProducts(): Flow<List<ProductEntity>>

    suspend fun refreshProducts(category: String): ResultWrapper<Unit>

    suspend fun getProductById(id: Int): ProductEntity?

    fun observeFavorites(): Flow<List<ProductEntity>>

   suspend fun addFavorite(productId: Int)

   suspend fun removeFavorite(productId: Int)

    suspend fun syncFavorites()
}