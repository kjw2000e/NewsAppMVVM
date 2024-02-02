package com.jiwon.newsappmvvm.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.jiwon.newsappmvvm.R
import com.jiwon.newsappmvvm.databinding.FragmentArticleBinding
import com.jiwon.newsappmvvm.databinding.FragmentSavedNewsBinding
import com.jiwon.newsappmvvm.model.Article
import com.jiwon.newsappmvvm.ui.NewsActivity
import com.jiwon.newsappmvvm.ui.NewsViewModel
import com.jiwon.newsappmvvm.util.Constants
import com.jiwon.newsappmvvm.util.Constants.Companion.BUNDLE_KEY_ARTICLE

class ArticleFragment : Fragment() {
    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentArticleBinding

    val args: ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val article = arguments?.getSerializable(BUNDLE_KEY_ARTICLE) as Article
//        Log.e("kjw", "arg url : ${article.url}")

        val article = args.article
        Log.e("kjw", "article : ")
//        Log.e("kjw", "article fragment, url : ${article.url}")

        binding.webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
            loadUrl(article.url)
        }

        viewModel = (activity as NewsActivity).viewModel

        binding.fab.setOnClickListener {
            viewModel.saveNews(article)
            Snackbar.make(view, "Successfully saved article", Snackbar.LENGTH_SHORT).show()
        }
    }
}