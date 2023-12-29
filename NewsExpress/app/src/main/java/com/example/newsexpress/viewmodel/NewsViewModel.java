package com.example.newsexpress.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsexpress.adapter.NewsRecyclerViewAdapter;
import com.example.newsexpress.model.NewsModel;
import com.example.newsexpress.repository.Repository;

import org.json.JSONException;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<NewsModel>> newsItems;
    private Repository repository;

    private Context context;


    public NewsViewModel(Context context){
        this.context = context;
        newsItems = new MutableLiveData<>();
        repository = new Repository(context);
    }

    public LiveData<List<NewsModel>> getNewsItems() {
        return newsItems;
    }

    public void fetchNewsData(){
        repository.getNewsItems(
                newsModels -> newsItems.setValue(newsModels),
                error -> {
                    // Handle error
                });
    }


}
