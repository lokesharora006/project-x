package com.example.quotesapp.data

import com.example.quotesapp.model.QuoteModel
import com.example.quotesapp.network.QuotesApiService

interface QuoteRepository {
    suspend fun getQuotes(): List<QuoteModel>
}

class NetworkQuoteRepository(
    private val quotesApiService: QuotesApiService,
    private val apiKey: String
): QuoteRepository{
    override suspend fun getQuotes(): List<QuoteModel> =
        quotesApiService.getQuotes(apiKey)
}