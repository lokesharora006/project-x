package com.example.newsexpress.retrofit;

import com.example.newsexpress.model.NewsModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import kotlin.jvm.Synchronized;

public class RetrofitModel {

    private List<NewsModel> articles;

    public List<NewsModel> getArticles() {
        return articles;
    }

//    public RetrofitModel(String articles){
//        this.articles = articles;
//    }
//
//    public String getArticles() {
//        return articles;
//    }
//
//    public void setArticles(String articles) {
//        this.articles = articles;
//    }
}
