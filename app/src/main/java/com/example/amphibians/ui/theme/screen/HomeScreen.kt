package com.example.amphibians.ui.screen

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.modul.Amphibian

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.spin_1s_200px), contentDescription = null)
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier,
    Retry:()->Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column {
            Image(modifier =modifier.size(300.dp),
                painter = painterResource(id = R.drawable.wifi_off_fill0_wght400_grad0_opsz24), contentDescription = null
            )
            Button(onClick = Retry ) {
                Text(text = "Retry")
            }
        }
    }
}

@Composable
fun ResultScreen(
    modifier: Modifier,
    amphibiansModule: Amphibian
) {
    Card (modifier=modifier.padding(12.dp)){
        Column {
            Text(modifier=modifier.padding(12.dp),
                fontSize = 25.sp,
                text = "${amphibiansModule.name} (${amphibiansModule.type})"
            )
            AsyncImage(
                modifier=modifier.padding(8.dp)
                    .fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .crossfade(true)
                    .data(amphibiansModule.imageCsr)
                    .build(),
                error = painterResource(id = R.drawable.wifi_off_fill0_wght400_grad0_opsz24),
                placeholder = painterResource(id = R.drawable.spin_1s_200px),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Text(modifier = modifier.padding(8.dp),
                fontSize = 15.sp,
                textAlign = TextAlign.Justify,
                text = amphibiansModule.description
            )
        }
    }
}

@Composable
fun ListOfAmphibians(
    modifier: Modifier,
    amphibians:List<Amphibian>
) {
    LazyColumn {
        items(amphibians){iterm->
            ResultScreen(modifier = modifier, amphibiansModule = iterm)
        }
    }
}