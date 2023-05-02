package com.example.catfactassignment;

import static androidx.core.view.ViewCompat.animate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView factView;
    private FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        factView = findViewById(R.id.fact);
        fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate(fab)
                        .setInterpolator(new OvershootInterpolator())
                        .setListener(null)
                        .rotation(fab.getRotation() + 360f)
                        .setDuration(1000).start();
                apiCall();
            }
        });

        apiCall();

    }

    public void apiCall(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String Url = "https://catfact.ninja/fact" ;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String factResponse = response.getString("fact");
                            factView.setText(factResponse);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                },error -> {

        });
        requestQueue.add(jsonObjectRequest);
    }
}