package com.example.navigationdrawertutorial

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 64.dp),
    contentAlignment = Alignment.Center){
        Text(text = "Header",fontSize = 60.sp)
    }
}

@Composable
fun DrawerBody(
    items: List<DrawerMenuItem>,
    modifier: Modifier= Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (DrawerMenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(imageVector = item.icon,
                    contentDescription = item.description)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title,
                    style = TextStyle(),
                    modifier = Modifier.weight(1f)
                    )
            }
        }
    }
}