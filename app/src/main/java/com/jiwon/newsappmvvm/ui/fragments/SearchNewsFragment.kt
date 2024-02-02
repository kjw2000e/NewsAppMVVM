package com.jiwon.newsappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiwon.newsappmvvm.R
import com.jiwon.newsappmvvm.adapters.NewsAdapter
import com.jiwon.newsappmvvm.databinding.FragmentSearchNewsBinding
import com.jiwon.newsappmvvm.ui.NewsActivity
import com.jiwon.newsappmvvm.ui.NewsViewModel
import com.jiwon.newsappmvvm.util.Constants
import com.jiwon.newsappmvvm.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.jiwon.newsappmvvm.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchNewsFragment : Fragment() {
    private val viewModel: NewsViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentSearchNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchNewsBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.getSearchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNewsList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success ->  {
                    hideProgressBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(response.data?.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("kjw", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        newsAdapter.setOnItemClickListener {
            // to navigate article fragment with article object.
            // but article object is not primitive. so we have to serialize.
            // and finally navigate to article fragment with bundle args.
            val bundle = Bundle().apply {
                putSerializable(Constants.BUNDLE_KEY_ARTICLE, it)
            }
            findNavController().navigate(R.id.action_searchNewsFragment_to_articleFragment, bundle)
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
}