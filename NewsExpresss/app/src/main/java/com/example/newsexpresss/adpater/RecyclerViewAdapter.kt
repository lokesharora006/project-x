package com.example.newsexpresss.adpater

import android.media.Image
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsexpresss.R
import com.example.newsexpresss.model.NewsModel
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val newsList: List<NewsModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val newsImage: ImageView = view.findViewById(R.id.newsImage)
        val newsTitle: TextView = view.findViewById(R.id.newsTitle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_recylerview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.newsTitle.text = newsList.get(position).title
        Picasso.get().load(newsList.get(position).imageSrc).into(holder.newsImage)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


}