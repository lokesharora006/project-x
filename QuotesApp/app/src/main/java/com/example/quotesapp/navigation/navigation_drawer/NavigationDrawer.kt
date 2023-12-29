package com.example.quotesapp.navigation.navigation_drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerHeader() {
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile",
            modifier = Modifier
                .clip(CircleShape)
        )
    }
}

@Composable
fun DrawerBody(
    items: List<DrawerMenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
    onItemClick: (DrawerMenuItem) -> Unit
    ) {
    LazyColumn(modifier){
        items(items){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(it)
                    }
                    .padding(16.dp)
            ) {
                Icon(imageVector = it.icon,
                contentDescription = it.description)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = it.title,
                    style = TextStyle(),
                    modifier = Modifier.weight(1f)
                    )
            }
        }
    }
}