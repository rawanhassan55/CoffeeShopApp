package com.example.coffeeshopapp.data.local.product

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsLocalDataSource @Inject constructor(
   private val dao: ProductsDao
) {
     fun getAllProducts() : Flow<List<ProductEntity>> =
        dao.getAllProducts()

    suspend fun getProductById(id: Int) : ProductEntity? =
        dao.getProductById(id)

    suspend fun saveProducts(products: List<ProductEntity>){
        dao.insertAll(products)
    }

     fun getFavoriteProducts() : Flow<List<ProductEntity>> =
        dao.getFavoriteProducts()

    suspend fun markAsFavorite(id:Int) = dao.markAsFavorite(id)

    suspend fun removeFavorite(id: Int) = dao.removeFavorite(id)

    suspend fun  clearFavorites() = dao.clearFavorites()


}