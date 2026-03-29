package com.example.coffeeshopapp.di

import com.example.coffeeshopapp.data.repository.cart.CartRepository
import com.example.coffeeshopapp.data.repository.cart.CartRepositoryImpl
import com.example.coffeeshopapp.data.repository.firebase.auth.FirebaseAuthRepository
import com.example.coffeeshopapp.data.repository.firebase.auth.FirebaseAuthRepositoryImpl
import com.example.coffeeshopapp.data.repository.firebase.firestore.ProductsRepository
import com.example.coffeeshopapp.data.repository.firebase.firestore.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductsRepository(
        impl: ProductsRepositoryImpl
    ): ProductsRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        impl: CartRepositoryImpl
    ): CartRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: FirebaseAuthRepositoryImpl
    ): FirebaseAuthRepository
}
