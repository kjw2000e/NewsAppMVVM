package com.jiwon.newsappmvvm.api

import com.jiwon.newsappmvvm.model.NewsResponse
import com.jiwon.newsappmvvm.util.Constants.Companion.API_KEY
import com.jiwon.newsappmvvm.util.Constants.Companion.SEARCH_SORT_TYPE_POPULARITY
import com.jiwon.newsappmvvm.util.Constants.Companion.SEARCH_SORT_TYPE_PUBLISHEDAT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "kr",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("sortBy")
        sortBy: String = SEARCH_SORT_TYPE_PUBLISHEDAT
    ): Response<NewsResponse>
}