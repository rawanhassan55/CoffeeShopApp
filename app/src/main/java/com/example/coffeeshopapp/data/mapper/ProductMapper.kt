package com.example.coffeeshopapp.data.mapper

import com.example.coffeeshopapp.data.local.product.ProductEntity
import com.example.coffeeshopapp.data.model.Product

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        price = this.price,
        imageUrl = this.imageUrl,
        category = this.category,
        rating = this.rating,
        subTitle = this.subtitle,

    )
}
