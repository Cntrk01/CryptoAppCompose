package com.compose.cryptoappcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.cryptoappcompose.model.CryptoListItem
import com.compose.cryptoappcompose.repository.CryptoRepository
import com.compose.cryptoappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(private val repository:CryptoRepository) : ViewModel() {
    //<List<CryptoListItem>> bu veri tipi oldugunu belirtmezsek hata veriyor T generic veremezsin diye
    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage= mutableStateOf("")
    var isLoading= mutableStateOf(false)

    //viewmodelscope ile burada tanımlama yapabiliriz.Yada bu fonksiyonuda suspend yapabiliriz.Fakat bunların composda birer dezavantajları var henüz bahsetmedik.
    fun loadCrypto()=viewModelScope.launch{
        isLoading.value=true
        val result=repository.getCryptoList()
        when(result){
            is Resource.Success->{
                //mapIndexed foreach mantıgındadır içindeki elemanları tek tek almamızı saglar
                val cryptoItem=result.data!!.mapIndexed { index, cryptoListItem ->
                    CryptoListItem(cryptoListItem.currency,cryptoListItem.price)
                } as List<CryptoListItem>
                errorMessage.value=""
                isLoading.value=false
                cryptoList.value += cryptoItem

            }
            is Resource.Error->{
                errorMessage.value=result.message!!
                isLoading.value=false
            }

            else -> {}
        }
    }
}