package com.example.newsexpress.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsexpress.api.ApiService;
import com.example.newsexpress.model.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository{


    private RequestQueue requestQueue;

    public Repository(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void getNewsItems(Response.Listener<List<NewsModel>> successListener, Response.ErrorListener errorListener) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "https://newsapi.org/v2/everything?q=tesla&apiKey=a8e9021dd36d403089f9d98ba7682fe7", null,
                response -> {
                    try {
                        List<NewsModel> userList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject userJson = response.getJSONObject(i);
                            NewsModel newsModelItem = new NewsModel(userJson.getString("urlToImage"),userJson.getString("title"));
                            userList.add(newsModelItem);
                        }
                        successListener.onResponse(userList);
                    } catch (JSONException e) {
                        errorListener.onErrorResponse(new VolleyError(e));
                    }
                },
                errorListener);

        requestQueue.add(request);
    }

//    public void fetchNewsItems(Context context) {
//
//        ApiService apiService = new ApiService(context);
//        JSONArray jsonArray = apiService.apiCall();
//
//        List<NewsModel> itemList = new ArrayList<>();
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                if (jsonArray != null) {
//                    String url = jsonArray.getJSONObject(i).getString("urlToImage");
//                    String headline = jsonArray.getJSONObject(i).getString("title");
//                    itemList.add(new NewsModel(url, headline));
//                }
//            }catch (JSONException e){
//                e.printStackTrace();
//            }
//        }
//
//        newsItems.setValue(itemList);
//    }

}
