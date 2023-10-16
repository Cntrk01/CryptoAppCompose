package com.compose.cryptoappcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.cryptoappcompose.viewmodel.CryptoListViewModel

@Composable
fun CryptoListScreen(navController: NavController,viewModel: CryptoListViewModel= hiltViewModel()){

    Surface(
        color=MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Text(text = "Crypto Crazy",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.surface)

            Spacer(modifier = Modifier.height(10.dp))
            //Search
            SearchBar(hint = "Search...", modifier = Modifier.fillMaxWidth().padding(16.dp)){
                //buradaki it searchbar içinde yazılan texti bize veriyor
            }
            Spacer(modifier = Modifier.height(10.dp))
            //List
        }
    }
}
@Composable
fun SearchBar(
    modifier: Modifier=Modifier,
    hint : String="",
    onSearch : (String)->Unit ={}
){
    //by ekleyerek valueye text verdiğimizde text.value yazmıyoruz.
    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint!="")
    }
//Box içine alma sebebimiz hint saçma biryerde gözüküyordu bundan dolayı boxa aldık
    Box(modifier = modifier){
        BasicTextField(value = text, onValueChange = {
            text =it
            onSearch(it) //yazılan texti buradaki fonksiyona vererek yazılan stringi almasını sağladık.
        }, maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color=Color.Black),
            modifier= Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape) //shadow gölge veriyor
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged { //kullanıcı buraya tıklamayı bıraktıktan sonra ne olacak diye
                    //searchbar boşsa hinti göstermemizi sağlıyor
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
        if (isHintDisplayed){
            Text(text = hint, color = Color.LightGray,modifier=Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
        }
    }
}