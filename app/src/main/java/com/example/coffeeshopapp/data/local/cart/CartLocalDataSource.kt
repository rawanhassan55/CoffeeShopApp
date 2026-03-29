package com.example.coffeeshopapp.data.local.cart

import javax.inject.Inject

class CartLocalDataSource @Inject constructor(
    private val dao: CartDao
) {
        fun observeCart() = dao.observeCart()

    suspend fun insert(item: CartEntity) =
        dao.insert(item)

    suspend fun getItem(productId: Int) =
        dao.getItem(productId)

        suspend fun updateQty(productId: Int, qty: Int) {
            dao.updateQuantity(productId, qty)
        }

        suspend fun remove(productId: Int) {
            dao.delete(productId)
        }

        suspend fun clear() = dao.clearCart()
    }






