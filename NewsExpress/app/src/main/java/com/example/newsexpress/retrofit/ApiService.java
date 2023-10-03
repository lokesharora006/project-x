package com.example.newsexpress.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static final String BASE_URL = "https://newsapi.org/v2/everything/";
    private static final String API_KEY = "a8e9021dd36d403089f9d98ba7682fe7";
//
//    private final Retrofit retrofit = new Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build();

//    public static Api apiMethod = null;
//
//    public static Api getApiMethod(){
//        if (apiMethod == null){
//            Retrofit retrofit = new Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl(BASE_URL)
//                    .build();
//
//            apiMethod = retrofit.create(Api.class);
//        }
//        return apiMethod;
//    }

//    Retrofit retrofit = new Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl(BASE_URL)
//                    .build();
//
//    Api api = retrofit.create(Api.class);


    private static ApiService instance = null;
    private Api myApi;

    private ApiService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }

}
