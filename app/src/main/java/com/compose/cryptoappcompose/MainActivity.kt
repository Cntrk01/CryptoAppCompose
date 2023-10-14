package com.compose.cryptoappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.cryptoappcompose.ui.theme.CryptoAppCompose
import com.compose.cryptoappcompose.view.CryptoDetailScreen
import com.compose.cryptoappcompose.view.CryptoListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppCompose {
                //değişkenlerden birisinde değişiklik yada composable da oldugunda yani detaydan geri geldiğimizde yeni verilerin gelmesi durumunda
                //recomposition işlemi olmaması için.Yani bütün ekranı değilde değişiklik olan fonksiyon değişkenlerin tekrar oluşmasını sağlıyor.Remember!
                //rememberNavController() NavController'ın yeniden oluşturulmasına neden olacaktır.
                val navController= rememberNavController()
                //startDestination içinde bulunan stringi kafama göre yazdım ve aşağıda verdiğim ilkcomposable benim startım oluyo
                NavHost(navController = navController, startDestination ="crypto_list_screen"){
                    composable("crypto_list_screen"){
                        //CryptoListScreen
                        //Sonradan oluşturdugum classları buraya tanımlayabiliyorum
                        //İlk çalıştıığı anda da bu ekranı görebiliyoruz.
                        CryptoListScreen(navController = navController)
                    }
                    //Kripto detay sayfasına biz argümanla parametre alacağını söyledik. /{cryptoId}/{cryptoPrice} bunları buraya ekleyerek verilerin böyle gönderileceğini söyledik.
                    composable("crypto_detail_string/{cryptoId}/{cryptoPrice}", arguments = listOf(
                        navArgument("cryptoId"){
                            type= NavType.StringType
                        },
                        navArgument("cryptoPrice"){
                            type=NavType.StringType
                        }
                    )){
                        //composable içinde verdiğimiz değişkenlere gelen değerleri artık burda listeleyebildik.
                        val cryptoId= remember {
                            it.arguments?.getString("cryptoId")
                        }
                        val cryptoPrice=remember {
                            it.arguments?.getString("cryptoPrice")
                        }
                        CryptoDetailScreen(
                            id = cryptoId ?: "",
                            price = cryptoPrice ?: "",
                            navController = navController)
                    }
                }
            }
        }
    }
}
