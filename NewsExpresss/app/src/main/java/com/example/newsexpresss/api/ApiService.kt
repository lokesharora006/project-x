package com.example.newsexpresss.api

import com.example.newsexpresss.model.NewsReponse
import com.example.newsexpresss.utils.Constant.Companion.API_KEY
import com.example.newsexpresss.utils.Constant.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object Api{
    val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}

interface ApiService {

    @GET("top-headlines")
    suspend fun getNewsList(
        @Query("apikey") key: String = API_KEY,
        @Query("country") country: String
    ): Response<NewsReponse>

}