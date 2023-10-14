package com.compose.cryptoappcompose.service

import retrofit2.http.GET
import retrofit2.http.Query

class CryptoAPI {

    //burada key yazılan yere API KEY veriyoruz.
    @GET("prices")
    suspend fun getCryptoList(
        @Query("key") key : String
    ) : Unit {
    }

    @GET("currencies")
    suspend fun getCrypto(
        @Query("key") key : String,
        @Query("ids") id  : String,
        @Query("attributes") attributes: String,
    ) : Unit {
    }
}