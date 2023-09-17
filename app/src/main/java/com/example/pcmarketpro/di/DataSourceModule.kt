package com.example.pcmarketpro.di

import com.example.pcmarketpro.data.source.local.LocalDataSourceImpl
import com.example.pcmarketpro.data.source.local.ProductFavoriteDAO
import com.example.pcmarketpro.data.source.remote.ProductService
import com.example.pcmarketpro.data.source.remote.RemoteDataSourceImpl
import com.example.pcmarketpro.domain.datasource.local.LocalDataSource
import com.example.pcmarketpro.domain.datasource.remote.RemoteDataSource
import com.example.pcmarketpro.domain.repository.Authenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        productService: ProductService,
        authenticator: Authenticator
    ): RemoteDataSource = RemoteDataSourceImpl(productService, authenticator)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        productFavoriteDAO: ProductFavoriteDAO,
        ioDispatcher: CoroutineContext
    ): LocalDataSource = LocalDataSourceImpl(productFavoriteDAO, ioDispatcher)

}