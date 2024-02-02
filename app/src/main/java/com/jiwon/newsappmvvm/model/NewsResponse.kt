package com.jiwon.newsappmvvm.model

import com.jiwon.newsappmvvm.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)