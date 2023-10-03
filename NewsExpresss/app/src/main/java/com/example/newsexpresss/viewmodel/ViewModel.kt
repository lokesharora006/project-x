package com.example.newsexpresss.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsexpresss.model.Article
import com.example.newsexpresss.model.NewsReponse
import com.example.newsexpresss.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel() : ViewModel() {

    private val repository = Repository()

    private val _newsItems = MutableLiveData<List<Article>>()

    val newsItem: LiveData<List<Article>> = _newsItems

    fun fetchNewsItems(country: String){
        viewModelScope.launch {
            val items = repository.getNewsItem(country)
            _newsItems.value = items
        }
    }


}