package com.example.newsexpress.api;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class ApiService {

    private Context context;
    private JSONArray jsonArray;

    public ApiService(Context context){
        this.context = context;
    }

    public JSONArray apiCall() {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String imageUrl = "https://newsapi.org/v2/everything?q=tesla&apiKey=a8e9021dd36d403089f9d98ba7682fe7";

        @SuppressLint("NotifyDataSetChanged")
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, imageUrl,
                null,
                response -> {
                    try {
                        jsonArray = response.getJSONArray("articles");
//                        newsList.clear();
//                        Log.d("json", Integer.toString(jsonArray.length()));
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            String url = jsonArray.getJSONObject(i).getString("urlToImage");
//                            String headline = jsonArray.getJSONObject(i).getString("title");
//                            newsList.add(new NewsModel(url, headline));
//                        }
//                        newsRecyclerViewAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {

        }

        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };

        requestQueue.add(jsonObjectRequest);

        return jsonArray;
    }
}
