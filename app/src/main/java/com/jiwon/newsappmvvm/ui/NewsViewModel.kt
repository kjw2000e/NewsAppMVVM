package com.jiwon.newsappmvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jiwon.newsappmvvm.model.Article
import com.jiwon.newsappmvvm.model.NewsResponse
import com.jiwon.newsappmvvm.repository.NewsRepository
import com.jiwon.newsappmvvm.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
): ViewModel() {
    private val _breakingNewsList: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsList: LiveData<Resource<NewsResponse>>
        get() = _breakingNewsList


    var breakingNewsPage = 1

    val searchNewsList: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage = 1

    init {
        getBreakingNews("kr", breakingNewsPage)
    }

    fun getBreakingNews(countryCode: String, pageNumber: Int) {
        viewModelScope.launch {
            // 로딩 시작
            _breakingNewsList.postValue(Resource.Loading())
            val response = newsRepository.getBreakingNews(countryCode, pageNumber)
            _breakingNewsList.postValue(handleBreakingNewsResponse(response))
        }
    }

    fun getSearchNews(searchQuery: String) {
        viewModelScope.launch {
            searchNewsList.postValue(Resource.Loading())
            val response = newsRepository.getSearchNews(searchQuery, searchNewsPage)
            searchNewsList.postValue(handleSearchNewsResponse(response))
        }
    }

    fun saveNews(article: Article) = viewModelScope.launch {
        newsRepository.saveNews(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteNews(article: Article) = viewModelScope.launch {
        newsRepository.deleteNews(article)
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { newsResponse ->
                return Resource.Success(newsResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { newsResponse ->
                return Resource.Success(newsResponse)
            }
        }
        return Resource.Error(response.message())
    }
}