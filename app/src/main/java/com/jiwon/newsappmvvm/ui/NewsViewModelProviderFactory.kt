package com.jiwon.newsappmvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jiwon.newsappmvvm.repository.NewsRepositoryImpl

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepositoryImpl
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}