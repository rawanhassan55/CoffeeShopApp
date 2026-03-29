package com.example.coffeeshopapp.di

import android.content.Context
import androidx.room.Room
import com.example.coffeeshopapp.data.local.cart.CartDao
import com.example.coffeeshopapp.data.local.product.ProductDatabase
import com.example.coffeeshopapp.data.local.product.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "coffee_shop_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductsDao(
        database: ProductDatabase
    ): ProductsDao {
        return database.productsDao()
    }

    @Provides
    fun provideCartDao(
        database: ProductDatabase
    ): CartDao {
        return database.cartDao()
    }
}
