package com.example.pcmarketpro.di

import android.content.Context
import androidx.room.Room
import com.example.pcmarketpro.data.source.local.FavoritesRoomDB
import com.example.pcmarketpro.data.source.local.ProductFavoriteDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideFavoritesRoomDB(@ApplicationContext appContext: Context): FavoritesRoomDB =
        Room.databaseBuilder(
            appContext,
            FavoritesRoomDB::class.java,
            "favorites_database.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideProductFavoriteDAO(favoritesRoomDB: FavoritesRoomDB): ProductFavoriteDAO =
        favoritesRoomDB.productFavoriteDAO()
}