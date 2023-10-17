package com.compose.cryptoappcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.compose.cryptoappcompose.model.Crypto
import com.compose.cryptoappcompose.repository.CryptoRepository
import com.compose.cryptoappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(private val repository: CryptoRepository):ViewModel(){

    suspend fun getCrypto(id:String) : Resource<Crypto>{
        return repository.getCrypto(id)
    }
}