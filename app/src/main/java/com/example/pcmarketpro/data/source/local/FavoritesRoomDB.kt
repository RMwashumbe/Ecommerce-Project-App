package com.example.pcmarketpro.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pcmarketpro.data.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class FavoritesRoomDB : RoomDatabase() {
    abstract fun productFavoriteDAO(): ProductFavoriteDAO
}