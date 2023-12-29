package com.example.newsexpress.retrofit;

import com.example.newsexpress.model.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("?q=Apple&apiKey=a8e9021dd36d403089f9d98ba7682fe7")
    Call<RetrofitModel> getNewsList();

}
