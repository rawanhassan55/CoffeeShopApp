package com.example.coffeeshopapp.data.repository.cart

import com.example.coffeeshopapp.data.local.cart.CartEntity
import com.example.coffeeshopapp.data.local.cart.CartLocalDataSource
import com.example.coffeeshopapp.data.model.Product
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val local: CartLocalDataSource
) : CartRepository {

    override fun observeCart() = local.observeCart()


    override suspend fun addToCart(product: Product, quantity: Int) {
        val  existingItem = local.getItem(product.id)

        if( existingItem == null)
        {
            local.insert(
                CartEntity(
                    productId = product.id,
                    name = product.name,
                    imageUrl = product.imageUrl,
                    priceAtAddTime = product.price,
                    quantity = quantity
                )
            )
        } else {
            local.updateQty(
                product.id,
                quantity
            )
        }


    }

    override suspend fun increaseQty(productId: Int) {
        val item = local.getItem(productId) ?: return
        local.updateQty(productId,item.quantity + 1)
    }

    override suspend fun decreaseQty(productId: Int) {
      val item = local.getItem(productId) ?: return
        if(item.quantity == 1){
            local.remove(productId)
        } else {
            local.updateQty(productId,item.quantity - 1)
        }
    }

    override suspend fun remove(productId: Int) {
        local.remove(productId)
    }

    override suspend fun clearCart() {
        local.clear()
    }
}
