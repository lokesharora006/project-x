package com.example.newsexpress.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsexpress.R;
import com.example.newsexpress.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

//    private ArrayList<NewsModel> newsList;
//    private Context context;
//
//    public NewsRecyclerViewAdapter(ArrayList<NewsModel> newsList, Context context) {
//        this.newsList = newsList;
//        this.context = context;
//    }

    private List<NewsModel> newsItems = new ArrayList<>();

    public void setNewsItems(List<NewsModel> newsItems) {
        this.newsItems = newsItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_news_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewAdapter.ViewHolder holder, int position) {
        NewsModel currentData = newsItems.get(position);

//        Glide.with().load(currentData.getImageSrc()).into(holder.newsImage);
        Picasso.get().load(currentData.getImageSrc()).into(holder.newsImage);
        holder.newsHeadline.setText(currentData.getNewsHeadline());
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsHeadline;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsHeadline = itemView.findViewById(R.id.newsTitle);

        }
    }
}
