package com.example.woof.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.woof.R

data class dog(
    @DrawableRes val imageResourceId: Int,
   @StringRes val name: Int,
   val age: Int,
   @StringRes val hobbies: Int
   )

val dogs = listOf(
    dog(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
    dog(R.drawable.lola, R.string.dog_name_2, 16, R.string.dog_description_2),
    dog(R.drawable.frankie, R.string.dog_name_3, 2, R.string.dog_description_3),
    dog(R.drawable.nox, R.string.dog_name_4, 8, R.string.dog_description_4),
    dog(R.drawable.faye, R.string.dog_name_5, 8, R.string.dog_description_5),
    dog(R.drawable.bella, R.string.dog_name_6, 14, R.string.dog_description_6),
    dog(R.drawable.moana, R.string.dog_name_7, 2, R.string.dog_description_7),
    dog(R.drawable.tzeitel, R.string.dog_name_8, 7, R.string.dog_description_8),
    dog(R.drawable.leroy, R.string.dog_name_9, 4, R.string.dog_description_9)
)