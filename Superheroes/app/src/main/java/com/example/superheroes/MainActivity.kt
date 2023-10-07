package com.example.superheroes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.HeroesRepository
import com.example.superheroes.data.superheroes
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SuperHeroesApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SuperHeroesApp(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() }
    ) {
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)){
            items(HeroesRepository.heroes){
                superHeroesItem(superheroes = it)
            }
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .fillMaxWidth()
        .size(56.dp),
    contentAlignment = Alignment.Center) {
    }
    Text(text = stringResource(id = R.string.app_name)
    , style = MaterialTheme.typography.h4)
}

@Composable
fun superHeroesItem(superheroes: superheroes, modifier: Modifier = Modifier){
    SuperHeroesCard(hero = superheroes.nameRes, des = superheroes.descriptionRes , image = superheroes.imageRes,modifier = Modifier.padding(16.dp))
}

@Composable
fun SuperHeroesCard(@StringRes hero: Int,@StringRes des: Int, @DrawableRes image: Int, modifier: Modifier = Modifier){
    Card(modifier = modifier, elevation = 2.dp) {
        Row(modifier= Modifier
            .fillMaxSize()
            .padding(16.dp)
            .sizeIn(minHeight = 72.dp)){
            Column(modifier = Modifier.weight(1f)) {
                Text(text = stringResource(id = hero),
                style = MaterialTheme.typography.h5)
                Text(text = stringResource(id = des),
                style= MaterialTheme.typography.body1)
            }
            Spacer(Modifier.width(16.dp))
            Box(modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(8.dp))) {

                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme {
        SuperHeroesApp()
    }
}