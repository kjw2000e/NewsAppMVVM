package com.jiwon.newsappmvvm.repository

import com.jiwon.newsappmvvm.api.NewsAPI
import com.jiwon.newsappmvvm.api.RetrofitInstance
import com.jiwon.newsappmvvm.db.ArticleDatabase
import com.jiwon.newsappmvvm.model.Article
import com.jiwon.newsappmvvm.model.NewsResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    val db: ArticleDatabase,
    val api: NewsAPI
) : NewsRepository
{
    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return api.getBreakingNews(countryCode, pageNumber)
    }

    override suspend fun getSearchNews(
        searchQuery: String,
        pageNumber: Int
    ): Response<NewsResponse> {
        return api.searchForNews(searchQuery, pageNumber)
    }

    override suspend fun saveNews(article: Article) = db.getArticleDao().upsert(article)

    override fun getSavedNews() = db.getArticleDao().getAllArticles()

    override suspend fun deleteNews(article: Article) = db.getArticleDao().deleteArticle(article)
}