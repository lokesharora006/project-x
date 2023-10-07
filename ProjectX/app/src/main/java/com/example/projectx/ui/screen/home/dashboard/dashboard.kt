package com.example.projectx.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectx.R
import kotlinx.coroutines.delay


@Composable
fun AutoImageList(){
    val images = listOf(
        R.drawable.image,
        R.drawable.image,
        R.drawable.image,
        R.drawable.image,
        R.drawable.image
    )

    ImageSliderWithIndicator(images = images)
}

@Composable
fun AutoImageSliderItem(imageRes: Int){
    Image(painter = (painterResource(id =imageRes)),
        contentDescription = null,
        contentScale = ContentScale.Crop,

        modifier = Modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(20.dp))
    )
}

@Composable
fun Indicator(active: Boolean){
    val color = if(active) Color(0xfff2994A) else Color.Gray
    val size = if(active) 10.dp else 10.dp
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .size(size)
    )
}

@Composable
fun ImageSliderWithIndicator(images: List<Int>){
    val currentIndex = remember{
        mutableStateOf(0)
    }

    LaunchedEffect(Unit){
        while(true){
            delay(3000)
            currentIndex.value = (currentIndex.value + 1) % images.size
        }
    }
    Box(
        modifier = Modifier
            .height(210.dp)
            .fillMaxWidth()
            .padding(24.dp)
            .background(Color.White)
    ) {
        AutoImageSliderItem(imageRes = images[currentIndex.value])

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(alignment = Alignment.BottomCenter)
                ,

            horizontalArrangement = Arrangement.Center
        ) {
            images.forEachIndexed { index, _ ->
                Indicator(active = index == currentIndex.value)
                if (index < images.size - 1) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}



@Composable
fun SellerDashboardHome() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8F9))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Box(
                Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .background(color = Color(0xFF98A8B8),
                        shape = RoundedCornerShape(size = 45.dp))
            ){
                Image(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier
                        .size(45.dp)

                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
                text = "PROJECT X",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.sen)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFC6E2A),
                )
            )
            Spacer(modifier = Modifier.width(50.dp))
            Box(
                Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .background(color = Color(0xFF98A8B8),
                        shape = RoundedCornerShape(size = 45.dp))
            ){
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(45.dp)
                )
            }
        }


        AutoImageList()

        Box(
            modifier = Modifier
                .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 92.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color(0xff6a5ae0))
            )

//            Image(
//                painter = painterResource(id = R.drawable.group778),
//                contentDescription = "Group 778",
//                modifier = Modifier
//                    .align(alignment = Alignment.TopStart)
//                    .offset(
//                        x = 100.dp,
//                        y = 0.dp
//                    )
//                    .requiredWidth(width = 376.dp)
//                    .requiredHeight(height = 129.dp)
//            )

            Text(
                text = "True or Dare",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 91.dp,
                        y = 25.dp
                    )
                    .clip(shape = RoundedCornerShape(16.dp))
            )


            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 18.dp
                    )
                    .requiredSize(size = 58.dp)
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(color = Color(0xfff2994a))
            )
            Image(
                painter = painterResource(id = R.drawable.solarbottlebold),
                contentDescription = "solar:bottle-bold",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 25.dp,
                        y = 25.dp
                    )
                    .requiredSize(size = 40.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
            )
        }


        Box(
            modifier = Modifier
                .padding(top = 8.dp, start = 24.dp, end = 24.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 92.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color(0xff9B51E0))
            )

//            Image(
//                painter = painterResource(id = R.drawable.group778),
//                contentDescription = "Group 778",
//                modifier = Modifier
//                    .align(alignment = Alignment.TopStart)
//                    .offset(
//                        x = 100.dp,
//                        y = 0.dp
//                    )
//                    .requiredWidth(width = 376.dp)
//                    .requiredHeight(height = 129.dp)
//            )

            Text(
                text = "I Never..",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 91.dp,
                        y = 25.dp
                    )
                    .clip(shape = RoundedCornerShape(16.dp))
            )


            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 18.dp
                    )
                    .requiredSize(size = 58.dp)
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(color = Color(0xfff2994a))
            )
            Image(
                painter = painterResource(id = R.drawable.solarbottlebold),
                contentDescription = "solar:bottle-bold",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 25.dp,
                        y = 25.dp
                    )
                    .requiredSize(size = 40.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
            )
        }


        Box(
            modifier = Modifier
                .padding(top = 8.dp, start = 24.dp, end = 24.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 92.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color(0xff27AE60))
            )

//            Image(
//                painter = painterResource(id = R.drawable.group778),
//                contentDescription = "Group 778",
//                modifier = Modifier
//                    .align(alignment = Alignment.TopStart)
//                    .offset(
//                        x = 100.dp,
//                        y = 0.dp
//                    )
//                    .requiredWidth(width = 376.dp)
//                    .requiredHeight(height = 129.dp)
//            )

            Text(
                text = "Who is most..",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 91.dp,
                        y = 25.dp
                    )
                    .clip(shape = RoundedCornerShape(16.dp))
            )


            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 17.dp,
                        y = 18.dp
                    )
                    .requiredSize(size = 58.dp)
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(color = Color(0xfff2994a))
            )
            Image(
                painter = painterResource(id = R.drawable.solarbottlebold),
                contentDescription = "solar:bottle-bold",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 25.dp,
                        y = 25.dp
                    )
                    .requiredSize(size = 40.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
            )
        }
    }
}


@Preview(widthDp = 375, heightDp = 812)
@Composable
private fun SellerDashboardHomePreview() {
    SellerDashboardHome()
}
