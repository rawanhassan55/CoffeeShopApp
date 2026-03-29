package com.example.coffeeshopapp.data.source.firestore

import com.example.coffeeshopapp.data.source.ProductsData
import com.example.coffeeshopapp.utils.Constants
import com.google.firebase.firestore.FirebaseFirestore

object FirestoreSeeder {

    fun seedProducts() {
        val firestore = FirebaseFirestore.getInstance()
        val products = ProductsData()

        products.forEach { product ->
            firestore.collection(Constants.PRODUCTS)
                .document(product.id.toString())
                //Take this entire product and place it inside a document in Fire store.
                .set(product)
        }
    }
}

/*
Temporary tool,We run it once
It fills the Fire store with data
 */