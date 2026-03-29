package com.example.coffeeshopapp.data.repository.firebase.firestore

import com.example.coffeeshopapp.data.auth.AuthDataSource
import com.example.coffeeshopapp.data.local.product.ProductEntity
import com.example.coffeeshopapp.data.local.product.ProductsLocalDataSource
import com.example.coffeeshopapp.data.mapper.toEntity
import com.example.coffeeshopapp.utils.result.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
private val remote : ProductsRemoteDataSource,
    private val local : ProductsLocalDataSource,
private val auth: AuthDataSource
) : ProductsRepository {

    override fun observeProducts(): Flow<List<ProductEntity>> {
       return local.getAllProducts()
    }

    override suspend fun refreshProducts(category: String): ResultWrapper<Unit> {
        return when(val result = remote.getProductsByCategory(category)) {
            is ResultWrapper.Success -> {
                val entities = result.data.map { it.toEntity() }
                local.saveProducts(entities)
                ResultWrapper.Success(Unit)
            }
            is ResultWrapper.Error -> {
                ResultWrapper.Error(result.message, result.cause)
            }
        }
    }

    override suspend fun getProductById(id: Int): ProductEntity? {
        return local.getProductById(id)
    }

    override fun observeFavorites(): Flow<List<ProductEntity>> {
        return local.getFavoriteProducts()
    }

   override suspend fun addFavorite(productId: Int){
       val userId = auth.getCurrentUserId()
           ?: throw IllegalStateException("User not logged in")

       remote.addFavorite(userId, productId)

       local.markAsFavorite(productId)
   }

    override suspend fun removeFavorite(productId: Int) {
        val userId = auth.getCurrentUserId()
            ?: throw IllegalStateException("User not logged in")

        remote.removeFavorite(userId, productId)

        local.removeFavorite(productId)
    }

    //sync favorites
    override suspend fun syncFavorites() {
        val userId = auth.getCurrentUserId() ?: return

        val favoriteIds = remote.getUserFavorites(userId)

        local.clearFavorites()

        favoriteIds.forEach {
            local.markAsFavorite(it)
        }
    }


}