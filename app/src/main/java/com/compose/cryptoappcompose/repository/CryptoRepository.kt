package com.compose.cryptoappcompose.repository

import com.compose.cryptoappcompose.model.Crypto
import com.compose.cryptoappcompose.model.CryptoList
import com.compose.cryptoappcompose.service.CryptoAPI
import com.compose.cryptoappcompose.util.Constants
import com.compose.cryptoappcompose.util.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

//activity çalıştığı sürece bu sınıfın geçerli olduğunu söylüyoruz
@ActivityRetainedScoped
class CryptoRepository @Inject constructor(private val api: CryptoAPI){

    suspend fun getCryptoList() : Resource<CryptoList>{
        val response=try {
            api.getCryptoList()
        }catch (e:Exception){
            return Resource.Error(e.toString(),null)
        }
        return Resource.Success(response)
    }
    suspend fun getCrypto(id:String) : Resource<Crypto>{
        val response=try {
            api.getCrypto()
        }catch (e:Exception){
            return Resource.Error(e.toString(),null)
        }
        return Resource.Success(response)
    }
}