package com.example.quotesapp.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.quotesapp.R
import com.example.quotesapp.navigation.navigation_drawer.DrawerBody
import com.example.quotesapp.navigation.navigation_drawer.DrawerHeader
import com.example.quotesapp.navigation.navigation_drawer.DrawerMenuItem
import kotlinx.coroutines.launch


//@Composable
//fun QuoteMain(
//    quoteUiState: QuoteUiState,
//    retryAction: () -> Unit
//) {
//    when(quoteUiState){
//        is QuoteUiState.Success -> QuoteScreen(quoteUiState.quoteData, retryAction = retryAction)
//        is QuoteUiState.Loading -> LoadingScreen()
//        is QuoteUiState.Error -> ErrorScreen()
//    }
//}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(modifier = modifier.size(200.dp),
        imageVector = Icons.Default.Refresh,
        contentDescription = "Loading")
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.ic_broken_image),
            contentDescription = "Error")
        Text(text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp))
        Button(onClick = {}) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuoteScreen(
    quoteUiState: QuoteUiState,
    retryAction: () -> Unit
) {


    var rotationTrigger by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (rememberUpdatedState(rotationTrigger).value) 360f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    DrawerMenuItem(
                        id = "favourite",
                        title = "Favorite",
                        icon = Icons.Default.Favorite,
                        description = "Go to Favorite Page"
                    )
                ),
                onItemClick = {

                }
            )
        }
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {

            NavigationDrawerIcon {
                scope.launch{
                    scaffoldState.drawerState.open()
                }
            }


            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(20.dp)
            ) {
                when(quoteUiState){
                    is QuoteUiState.Success -> QuoteItem(quoteUiState.quoteData[0].quote, quoteUiState.quoteData[0].author)
                    is QuoteUiState.Loading -> LoadingScreen()
                    is QuoteUiState.Error -> ErrorScreen()
                }

            }

            Row (
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(20.dp, 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Image(imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription ="Favorite",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(25.dp)
                        .align(alignment = Alignment.CenterVertically)
                )
                Box(modifier = Modifier
                    .clip(shape = RoundedCornerShape(40.dp)
                    )
                ){
                    Image(
                        painter = painterResource(id = R.drawable.refresh),
                        contentDescription = "Refresh",
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .background(Color.White)
                            .size(50.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .padding(10.dp)
                            .rotate(rotationAngle)
                            .clickable {
                                rotationTrigger = !rotationTrigger
                                // retry action
                                retryAction()
                            },
                        contentScale = ContentScale.Fit,
                    )
                }
                Image(imageVector = Icons.Outlined.Send,
                    contentDescription ="Send",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(25.dp)
                        .align(alignment = Alignment.CenterVertically)
                        .clickable {
                            when(quoteUiState){
                                is QuoteUiState.Success ->
                                    shareQuote(context, quoteUiState.quoteData[0].quote, quoteUiState.quoteData[0].author)
                                else -> {}
                            }
                        }
                )
            }
        }
    }


}

@Composable
fun QuoteItem(quote: String, author: String) {

    Box(
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier

        ) {
            Row {
                Image(
                    imageVector = Icons.Filled.FormatQuote,
                    contentDescription = "Quote",
                    Modifier
                        .rotate(180f)
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.fillMaxWidth())
            }
            Row {
                Spacer(
                    modifier = Modifier
                        .width(40.dp)
                )
                Column {
                    Text(
                        text = quote,
                        style = MaterialTheme.typography.h5,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontWeight = FontWeight.Bold,
                        color = Color.White

                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = author,
                        style = MaterialTheme.typography.body1,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

//Navigation Drawer
@Composable
fun NavigationDrawerIcon(
    onNavigationIconCLick: () -> Unit
) {
    IconButton(onClick = onNavigationIconCLick,
        modifier = Modifier
            .padding(20.dp)
        ,
    ){
        Icon(imageVector = Icons.Default.Menu,
            contentDescription = "ToggleDrawer"
        ,Modifier.size(40.dp))
    }
}


//Function for share Quote and Navigate to other apps
private fun shareQuote(context: Context,quote: String, author: String){
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "$quote - $author")
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_quote)
        )
    )
}
