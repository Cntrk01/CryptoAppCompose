package com.compose.cryptoappcompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.compose.cryptoappcompose.model.Crypto
import com.compose.cryptoappcompose.util.Resource
import com.compose.cryptoappcompose.viewmodel.CryptoDetailViewModel
import kotlinx.coroutines.launch

//@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CryptoDetailScreen(
    id:String,
    price:String,
    navController: NavController,
    viewModel:CryptoDetailViewModel = hiltViewModel()){


//    val scope = rememberCoroutineScope()
//
//    var cryptoItem by remember { mutableStateOf<Resource<Crypto>>(Resource.Loading())}
//
//    //bu fonk. sürekli istek atılıyor.Biz remember kullanıyoruz böyle olmaması için fakat sürekli istek atıyor CryptoDetailScreen fonksiyonu sürekli çağırılmasına sebeb oluo
//    scope.launch {
//        cryptoItem = viewModel.getCrypto(id)
//        println(cryptoItem.data)
//    }
    //Adamlar bunun yerine LaunchedEffect kullanın demişler.Bu yukarıdakine göre daha iyi
//    var cryptoItem by remember { mutableStateOf<Resource<Crypto>>(Resource.Loading())}
//    LaunchedEffect(key1 = Unit, block ={
//        cryptoItem = viewModel.getCrypto(id)
//    } )

    //buda son yöntem en iyisi tekk satırda yapılıyor daha performanslı
    val cryptoItem by produceState<Resource<Crypto>>(initialValue = Resource.Loading()){
        value=viewModel.getCrypto(id)
    } // yada by silip bunun sonuna .value yazabiliriz
    //!!!!!!!! sadece 1 tane detail endpoint eklediğim için sadece btc geliyor .
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when (cryptoItem) {

                is Resource.Success -> {
                    val selectedCrypto = cryptoItem.data!![0]
                    Text(
                        text = selectedCrypto.name,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = rememberImagePainter(data = selectedCrypto.logo_url),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp, 200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )

                    Text(
                        text = price,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        textAlign = TextAlign.Center
                    )
                }

                is Resource.Error -> {
                    Text(cryptoItem.message!!)
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}