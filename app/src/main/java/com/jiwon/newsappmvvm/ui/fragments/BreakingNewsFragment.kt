package com.jiwon.newsappmvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiwon.newsappmvvm.R
import com.jiwon.newsappmvvm.adapters.NewsAdapter
import com.jiwon.newsappmvvm.databinding.FragmentBreakingNewsBinding
import com.jiwon.newsappmvvm.ui.NewsActivity
import com.jiwon.newsappmvvm.ui.NewsViewModel
import com.jiwon.newsappmvvm.util.Constants.Companion.BUNDLE_KEY_ARTICLE
import com.jiwon.newsappmvvm.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingNewsFragment : Fragment() {

    private val viewModel: NewsViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentBreakingNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreakingNewsBinding.inflate(
            inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = (activity as NewsActivity).viewModel
        viewModel.breakingNewsList.observe(viewLifecycleOwner, Observer { response ->
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

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        newsAdapter.setOnItemClickListener {
            // to navigate article fragment with article object.
            // but article object is not primitive. so we have to serialize.
            // and finally navigate to article fragment with bundle args.

            try {
                val bundle = Bundle().apply {
                    putSerializable(BUNDLE_KEY_ARTICLE, it)
                }
                findNavController().navigate(
                    R.id.action_breakingNewsFragment_to_articleFragment,
                    bundle
                )
            } catch(e: Exception) {

            }
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
}