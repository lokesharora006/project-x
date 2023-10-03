package com.example.quotesapp.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class QuoteModel (
    @SerialName(value = "quote") val quote: String ,
    @SerialName(value = "author") val author: String
    )