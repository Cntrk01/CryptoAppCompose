package com.compose.cryptoappcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.cryptoappcompose.model.CryptoListItem
import com.compose.cryptoappcompose.repository.CryptoRepository
import com.compose.cryptoappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(private val repository:CryptoRepository) : ViewModel() {

    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var errorMessage= mutableStateOf("")
    var isLoading= mutableStateOf(false)

    private var initialCryptoList= listOf<CryptoListItem>()
    private var isSearchStarting=true

    init {
        loadCrypto()
    }

    fun searchCryptoList(query:String){
        val listToSearch=if (isSearchStarting){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch (Dispatchers.Default){
            if (query.isEmpty()){
                cryptoList.value=initialCryptoList
                isSearchStarting=true
                return@launch
            }
            val result=listToSearch.filter {
                it.currency.contains(query.trim(),ignoreCase = true)
            }
            if (isSearchStarting){
                initialCryptoList=cryptoList.value
                isSearchStarting=false
            }
            cryptoList.value=result
        }
    }

    private fun loadCrypto()=viewModelScope.launch{
        isLoading.value=true
        val result=repository.getCryptoList()
        when(result){
            is Resource.Success->{
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