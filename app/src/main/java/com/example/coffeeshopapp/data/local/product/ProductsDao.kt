package com.example.coffeeshopapp.data.local.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun insertAll(product: List<ProductEntity>)

  @Query("SELECT * From products")
   fun getAllProducts() : Flow<List<ProductEntity>>

  //(:) it's placeholder which takes value from parameter
  @Query("SELECT * From products WHERE id = :productId")
  suspend fun getProductById(productId :Int) : ProductEntity?

    @Query("SELECT * FROM products WHERE id = :productId")
    fun observeProductById(productId: Int): Flow<ProductEntity>

  @Query("SELECT * From products WHERE isFavorite = 1")
   fun getFavoriteProducts() : Flow<List<ProductEntity>>

    @Query("UPDATE products SET isFavorite = 1 WHERE id = :productId")
  suspend fun  markAsFavorite(productId:Int)

    @Query("UPDATE products SET isFavorite = 0 WHERE id = :productId")
    suspend fun removeFavorite(productId: Int)

    @Query("UPDATE products SET isFavorite = 0")
    suspend fun clearFavorites()
}