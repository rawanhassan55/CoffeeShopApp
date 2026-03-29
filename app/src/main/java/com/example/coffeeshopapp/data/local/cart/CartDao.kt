package com.example.coffeeshopapp.data.local.cart

import androidx.room.Dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

   @Query("SELECT * FROM cart")
   fun observeCart(): Flow<List<CartEntity>>

   @Query("SELECT * FROM cart WHERE productId = :productId LIMIT 1")
   suspend fun getItem(productId: Int): CartEntity?

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(item: CartEntity)

   @Query("UPDATE cart SET quantity = :qty WHERE productId = :productId")
   suspend fun updateQuantity(productId: Int, qty: Int)

   @Query("DELETE FROM cart WHERE productId = :productId")
   suspend fun delete(productId: Int)

   //for future : if you want to remove all products
   @Query("DELETE FROM cart")
   suspend fun clearCart()
}
