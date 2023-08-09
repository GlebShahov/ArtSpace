package com.example.artspace

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import androidx.compose.material3.Icon as Icon1

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
fun GreetingArtSpace(image: Int, titleText: Int, artistText: Int, clickOnPreviousButton:()-> Unit, clickOnNextButton:()-> Unit) {




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
                Text(text = stringResource(id = titleText), fontSize = 24.sp)
                Text(
                    text = stringResource(id = artistText),
                    fontSize = 16.sp,
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

    fun clickOnButtonNext(){
        if(pictureNumber<5) pictureNumber++ else {pictureNumber ==5}

    }


    fun clickOnButtonPrevious(){
        if(pictureNumber>1) pictureNumber-- else{pictureNumber==1}
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
                titleText = R.string.tittle1,
                artistText = R.string.artist1,
                { clickOnButtonPrevious() }
            ) { clickOnButtonNext() }

        }

        2 -> {
            GreetingArtSpace(
                image = R.drawable.digital_art2,
                titleText = R.string.tittle2,
                artistText = R.string.artist2,
                { clickOnButtonPrevious() }
            ) { clickOnButtonNext() }

        }

        3 -> {
            GreetingArtSpace(
                image = R.drawable.digital_art3,
                titleText = R.string.tittle3,
                artistText = R.string.artist3,
                { clickOnButtonPrevious() }
            ) { clickOnButtonNext() }

        }

        4 -> {
            GreetingArtSpace(
                image = R.drawable.digital_art4,
                titleText = R.string.tittle4,
                artistText = R.string.artist4,
                { clickOnButtonPrevious() }
            ) { clickOnButtonNext() }

        }

        5 -> {
            GreetingArtSpace(
                image = R.drawable.digital_art5,
                titleText = R.string.tittle5,
                artistText = R.string.artist5,
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