package com.compose.cryptoappcompose.di

import com.compose.cryptoappcompose.repository.CryptoRepository
import com.compose.cryptoappcompose.service.CryptoAPI
import com.compose.cryptoappcompose.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCryptoRepository(
        api: CryptoAPI
    ) = CryptoRepository(api)

    @Singleton
    @Provides
    fun provideCryptoApi() : CryptoAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(CryptoAPI::class.java)
    }
}