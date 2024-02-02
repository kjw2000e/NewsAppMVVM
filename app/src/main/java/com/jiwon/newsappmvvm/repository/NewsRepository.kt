package com.jiwon.newsappmvvm.repository

import androidx.lifecycle.LiveData
import com.jiwon.newsappmvvm.model.Article
import com.jiwon.newsappmvvm.model.NewsResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse>

    suspend fun getSearchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse>

    suspend fun saveNews(article: Article): Long

    fun getSavedNews(): LiveData<List<Article>>

    suspend fun deleteNews(article: Article)
}