package com.example.newsexpresss.model

data class NewsReponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)