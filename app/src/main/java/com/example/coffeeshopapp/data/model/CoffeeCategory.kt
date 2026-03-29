package com.example.coffeeshopapp.data.model

enum class CoffeeCategory {
    CAPPUCCINO,
    LATTE,
    ESPRESSO,
    AMERICANO;

companion object {
    // convert string from firebase to enum
    fun fromString(value: String?): CoffeeCategory {
        return try {
            value?.uppercase()?.let { name ->
                entries.first { it.name == name }
            } ?: ESPRESSO // null -> default
        } catch (e: NoSuchElementException) {
            ESPRESSO // if not match -> default
        }
    }
}
    //to display name in specific way
    fun displayName(): String {
        return name.lowercase().replaceFirstChar { it.uppercase() }
    }
}