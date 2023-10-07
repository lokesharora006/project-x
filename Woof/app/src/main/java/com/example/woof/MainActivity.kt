package com.example.woof

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.woof.data.dog
import com.example.woof.data.dogs
import com.example.woof.ui.theme.Green50
import com.example.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WoofApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WoofApp() {
    Scaffold(
        topBar = {
            AppTopBar()
        }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(dogs) {
                DogItem(dog = it)
            }
        }
    }
}

@Composable
fun AppTopBar(modifier: Modifier = Modifier){
    Row(modifier = modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.primary),
    verticalAlignment = Alignment.CenterVertically){
        Image(modifier = Modifier
            .size(64.dp)
            .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_woof_logo),
            contentDescription = null)
        Text(text = "Woof",
        style = MaterialTheme.typography.h6)
    }

}

@Composable
fun DogItem(dog: dog, modifier: Modifier = Modifier){

    var expanded by remember { mutableStateOf(false) }

    val color by animateColorAsState(targetValue =  if (expanded) Green50 else MaterialTheme.colors.surface,)

    Card(modifier  = modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(10)),elevation=4.dp){
        Column(modifier = Modifier.animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ).background(color = color)) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                DogIcon(dogIcon = dog.imageResourceId)
                DogInfo(dogName = dog.name, dogAge = dog.age)
                Spacer(Modifier.weight(1f))
                DogItemButton(expanded = expanded, onClick = { expanded = !expanded })
            }
            if(expanded) {
                DogHobby(dogHobby = dog.hobbies)
            }
        }
    }
}

@Composable
private fun DogItemButton(expanded: Boolean, onClick: () -> Unit,
                          modifier: Modifier = Modifier){
    IconButton(onClick = onClick){
        Icon(imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
        tint = MaterialTheme.colors.secondary,
        contentDescription = stringResource(R.string.expand_button_content_description))
    }
}

@Composable
fun DogHobby(@StringRes dogHobby: Int, modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(
        start = 16.dp,
        top = 8.dp,
        bottom = 16.dp,
        end = 16.dp
    )){
        Text(text = "About:",
        style= MaterialTheme.typography.h6)
        Text(text = stringResource(id = dogHobby),
        style = MaterialTheme.typography.body1)
    }
}

@Composable
fun DogIcon(@DrawableRes dogIcon: Int, modifier: Modifier = Modifier){
    Image(modifier = modifier
        .size(64.dp)
        .padding(8.dp)
        .clip(RoundedCornerShape(50)),
        painter = painterResource(id = dogIcon),
        contentDescription = null ,
        contentScale = ContentScale.Crop)
}

@Composable
fun DogInfo(@StringRes dogName: Int , @StringRes dogAge:Int, modifier: Modifier = Modifier){
    Column{
        Text(text = stringResource(id = dogName),
        modifier = modifier.padding(top = 8.dp))
        Text(text = stringResource(id = R.string.years_old,dogAge))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WoofTheme {
        WoofApp()
    }
}