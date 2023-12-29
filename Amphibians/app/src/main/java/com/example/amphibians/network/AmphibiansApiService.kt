package com.example.amphibians.network

import com.example.amphibians.model.AmphibiansModel
import retrofit2.http.GET

interface AmphibiansApiService{

    @GET("amphibians")
    suspend fun getAmphibiansData(): List<AmphibiansModel>
}