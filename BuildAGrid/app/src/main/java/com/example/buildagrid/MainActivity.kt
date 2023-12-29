package com.example.buildagrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buildagrid.model.DataIntitiy
import com.example.buildagrid.ui.theme.BuildAGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildAGridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BuildAGridApp()
                }
            }
        }
    }
}

@Composable
fun BuildAGridApp() {

}

@Composable
fun AppCard(dataIntitiy: DataIntitiy, modifier: Modifier= Modifier){
//    Card(elevation = 4.dp) {
//        Row() {
//            Image(painter = painterResource(id = dataIntitiy.ImageResourceId), contentDescription = stringResource(
//                id = dataIntitiy.StringResourceId),
//            modifier = Modifier.fillMaxWidth(),
//            contentScale = ContentScale.Crop)
//
//            Column {
//                Text(text = stringResource(id = dataIntitiy.StringResourceId),
//                modifier = Modifier.padding(16.dp),
//                style = MaterialTheme.typography.body2)
//            }
//        }
//    }

    Card(elevation = 4.dp) {
        Row {
            androidx.compose.foundation.layout.Box(){
                Image(
                    painter = painterResource(id = dataIntitiy.ImageResourceId),
                    contentDescription = null,
                    modifier = modifier
                        .size(64.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }

            Column {
                Text(
                    text = stringResource(id = dataIntitiy.StringResourceId),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        painter = painterResource(R.drawable.ic_grain),
//                        contentDescription = null,
//                        modifier = Modifier
//                            .padding(start = 16.dp)
//                    )
                    Text(
                        text = dataIntitiy.value.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardPreveiw(){
    AppCard(dataIntitiy = DataIntitiy(R.string.architecture,100,R.drawable.architecture))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BuildAGridTheme {
        BuildAGridApp()
    }
}