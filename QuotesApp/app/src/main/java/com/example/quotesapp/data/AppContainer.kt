package com.example.quotesapp.data

import com.example.quotesapp.network.QuotesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val quoteRepository: QuoteRepository
}

class DefaultAppContainer(private  val apiKey: String) : AppContainer{
    private val baseUrl = "https://api.api-ninjas.com/v1/"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: QuotesApiService by lazy {
        retrofit.create(QuotesApiService :: class.java)
    }

    override val quoteRepository: QuoteRepository by lazy{
        NetworkQuoteRepository(retrofitService, apiKey)
    }
}