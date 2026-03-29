package com.example.coffeeshopapp.data.repository.firebase.firestore

import com.example.coffeeshopapp.data.model.Product
import com.example.coffeeshopapp.utils.result.ResultWrapper
import com.example.coffeeshopapp.utils.result.safeCall
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductsRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getProductsByCategory(category: String)
            : ResultWrapper<List<Product>> = safeCall {

        firestore.collection("products")
            .whereEqualTo("category", category)
            .get()
            .await()
            .toObjects(Product::class.java)
    }

    //userId → UID from FirebaseAuth , productId → ID product
    suspend fun addFavorite(userId: String, productId: Int) {
        firestore.collection("users")
            .document(userId)
            .collection("favorites")
            .document(productId.toString())
            .set(mapOf("productId" to productId))
            .await()

    }

    suspend fun removeFavorite(userId: String, productId: Int) {
        firestore.collection("users")
            .document(userId)
            .collection("favorites")
            .document(productId.toString())
            .delete()
            .await()
    }

    suspend fun getUserFavorites(userId: String): List<Int> {
        val documents = firestore.collection("users")
            .document(userId)
            .collection("favorites")
            .get()
            .await()
            .documents

        return documents.mapNotNull { it.getLong("productId")?.toInt() }
    }
}
