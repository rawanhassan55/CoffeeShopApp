package com.example.coffeeshopapp.data.local.product

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coffeeshopapp.data.local.cart.CartDao
import com.example.coffeeshopapp.data.local.cart.CartEntity


@Database(
    entities = [
        ProductEntity::class,
    CartEntity::class
               ],
    version = 4
)
 abstract class ProductDatabase : RoomDatabase(){

     abstract fun productsDao() : ProductsDao

     abstract fun cartDao() : CartDao
}