package com.example.newsexpress2

import android.annotation.SuppressLint
import android.widget.SearchView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentResolverCompat

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewsMainScreen(){

    var text by remember{ mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
    ) {

        Column{
            Text(
                text = "NewsExpress",
                fontSize = 26.sp,
                style = MaterialTheme.typography.body1,
                fontFamily = FontFamily(Font(R.font.lilita_one_regular))
            )


            SearchBar(
                query = text,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    active = false
                },
                active = active,
                onActiveChange = {
                    active=it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription ="SearchIcon" )
                },
                trailingIcon = {
                    if(active){
                        Icon(
                            modifier = Modifier.clickable {
                                if(text.isNotEmpty()){
                                    text = ""
                                }else{
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "CloseIcon")

                    }
                }
            ){

            }
        }




    }




//    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

//    Scaffold(
//        Text(text = "NewsExpress"
//            )
////        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
//    ){
//        Box(modifier = Modifier.fillMaxSize()){
//
//        }
//    }
}

//@Composable
//fun NewsAppBar(modifier: Modifier = Modifier){
//    TopAppBar(
//        title = { Text(text = "NewsExpress")},
//
//
//    )
//}

