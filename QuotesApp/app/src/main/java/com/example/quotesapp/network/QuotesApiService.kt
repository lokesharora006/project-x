package com.example.quotesapp.network

import com.example.quotesapp.model.QuoteModel
import retrofit2.http.GET
import retrofit2.http.Header

interface QuotesApiService {
    @GET("quotes")
    suspend fun getQuotes(
        @Header("X-Api-Key") apiKey: String
    ): List<QuoteModel>
}