package com.example.buildagrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DataIntitiy(
    @StringRes val StringResourceId : Int,
    val value: Int,
    @DrawableRes val ImageResourceId : Int
)
