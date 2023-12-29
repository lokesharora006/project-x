@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibiansModel(

    val name: String,
    val type: String,
    val description: String,
    @SerialName(value = "img_src") val imgSrc: String
)
