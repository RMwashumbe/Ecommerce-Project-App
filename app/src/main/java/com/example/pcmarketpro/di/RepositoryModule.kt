package com.example.pcmarketpro.di

import com.example.pcmarketpro.data.repository.ProductsRepositoryImpl
import com.example.pcmarketpro.domain.datasource.local.LocalDataSource
import com.example.pcmarketpro.domain.datasource.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): ProductsRepository = ProductsRepositoryImpl(remoteDataSource, localDataSource)
}