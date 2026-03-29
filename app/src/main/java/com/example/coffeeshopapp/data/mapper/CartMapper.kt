package com.example.coffeeshopapp.data.mapper

import com.example.coffeeshopapp.data.local.cart.CartEntity
import com.example.coffeeshopapp.data.model.CartItemUi

fun CartEntity.toUiModel() : CartItemUi {
  return  CartItemUi(
      productId = productId,
      name = name,
      image = imageUrl,
      price = priceAtAddTime,
      quantity = quantity,
      total = priceAtAddTime * quantity
    )

}