package com.compose.cryptoappcompose.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CryptoDetailScreen(
    id:String,
    price:String,
    navController: NavController){

    Text(text = "Hello CryptoDetailScreen")
}