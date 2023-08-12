package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.Retrofit.API
import com.example.artspace.ui.theme.ArtSpaceTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                        GreetingSpaceApp()


                }
            }
        }
    }
}

@Composable
fun GreetingArtSpace(
    image: Int,
    productName:String,
    productDescription: String,
    clickOnPreviousButton:()-> Unit, clickOnNextButton:()-> Unit) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Surface(
                modifier = Modifier
                    .size(220.dp)
                    .padding(top = 20.dp),
                border = BorderStroke(2.dp, color = Color.Black)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "null",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = productName, fontSize = 24.sp)
                Text(
                    text = productDescription,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    clickOnPreviousButton()

                }) {
                    Text(
                        text = "Previous",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Button(onClick = {
                    clickOnNextButton()

                }) {
                    Text(text = "Next")
                }
            }
        }

}


@Composable
fun GreetingSpaceApp(){
    var pictureNumber by remember { mutableStateOf(1) }
    var productTitle by remember { mutableStateOf("") }
    var productCategory by remember { mutableStateOf("") }
    var listOfProduct: List<String> by remember { mutableStateOf(listOf()) }


    //для отслеживания ответа в логах
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    //RETROFIT
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/").client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(API::class.java)


    //при клике меняем содержимое
    fun clickOnButtonNext(){
        if(pictureNumber<5) pictureNumber++ else {pictureNumber ==5}
        CoroutineScope(Dispatchers.IO).launch{
          val product = api.getProductById(pictureNumber)
            productTitle = product.title
            productCategory = product.description
        }

    }

    //при клике меняем содержимое
    fun clickOnButtonPrevious(){
        if(pictureNumber>1) pictureNumber-- else{pictureNumber==1}
        CoroutineScope(Dispatchers.IO).launch{
            val product = api.getProductById(pictureNumber)
            productTitle = product.title
            productCategory = product.description
           // val productImage = product.images.get(1)

        }
    }



val previousPicture = SwipeAction(
    onSwipe = {clickOnButtonPrevious()},
    icon = { },
    background = Color.White

)

val nextPicture = SwipeAction(
    onSwipe = {clickOnButtonNext()},
    icon = {},
    background = Color.White
)

SwipeableActionsBox(
startActions = listOf(previousPicture),
endActions = listOf(nextPicture),
    swipeThreshold = 5.dp
) {
        when (pictureNumber) {
        1 -> {

            GreetingArtSpace(
                image = R.drawable.digital_art,
                productName = productTitle,
                productDescription = productCategory,
                { clickOnButtonPrevious() }
            ) { clickOnButtonNext() }

        }

        2 -> {
            GreetingArtSpace(
                image = R.drawable.digital_art2,
                productName = productTitle,
                productDescription = productCategory,
                { clickOnButtonPrevious() }
            ) { clickOnButtonNext() }

        }

        3 -> {
            GreetingArtSpace(
                image = R.drawable.digital_art3,
                productName = productTitle,
                productDescription = productCategory,
                { clickOnButtonPrevious() }
            ) { clickOnButtonNext() }

        }

        4 -> {
            GreetingArtSpace(
                image = R.drawable.digital_art4,
                productName = productTitle,
                productDescription = productCategory,
                { clickOnButtonPrevious() }
            ) { clickOnButtonNext() }

        }

        5 -> {
            GreetingArtSpace(
                image = R.drawable.digital_art5,
                productName = productTitle,
                productDescription = productCategory,
                { clickOnButtonPrevious() }
            ) { clickOnButtonNext() }

        }

    }
}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        GreetingSpaceApp()
    }
}