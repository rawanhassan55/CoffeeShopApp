package com.example.coffeeshopapp.data.mapper

import com.example.coffeeshopapp.data.local.product.ProductEntity
import com.example.coffeeshopapp.data.model.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        description = description,
        price = price,
        imageUrl = imageUrl,
        category = category,
        rating = rating,
        subtitle = subTitle,
        isFavorite = isFavorite
    )
}
