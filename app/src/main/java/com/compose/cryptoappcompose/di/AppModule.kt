package com.compose.cryptoappcompose.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
//SingletonComponent bütün app çalışırken buradan erişileceğini söylüyoruz
@InstallIn(SingletonComponent::class)
object AppModule {

}