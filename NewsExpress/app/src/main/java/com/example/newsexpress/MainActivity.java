package com.example.newsexpress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsexpress.adapter.NewsRecyclerViewAdapter;
import com.example.newsexpress.model.NewsModel;
import com.example.newsexpress.retrofit.Api;
import com.example.newsexpress.retrofit.ApiService;
import com.example.newsexpress.retrofit.RetrofitModel;
import com.example.newsexpress.viewmodel.NewsViewModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private NewsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter();
        recyclerView.setAdapter(newsRecyclerViewAdapter);

        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.getNewsItems().observe(this, newsItems ->{
            newsRecyclerViewAdapter.setNewsItems(newsItems);
        } );

        viewModel.fetchNewsData();
    }

    public void init(){
        recyclerView = findViewById(R.id.recyclerView);
    }



}