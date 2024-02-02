package com.jiwon.newsappmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.jiwon.newsappmvvm.R
import com.jiwon.newsappmvvm.databinding.ActivityNewsBinding
import com.jiwon.newsappmvvm.db.ArticleDatabase
import com.jiwon.newsappmvvm.repository.NewsRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

//    val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.flFragment) as NavHostFragment
        val navController = navHostFragment.navController
        // 위 방식이나 아래나 똑같음
//        binding.bottomNavigationView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

//        val repository = NewsRepositoryImpl(ArticleDatabase.getInstance(this))
//        val newsViewModelProviderFactory = NewsViewModelProviderFactory(repository)
//        viewModel = ViewModelProvider(this, newsViewModelProviderFactory).get(NewsViewModel::class.java)
    }
}
