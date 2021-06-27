package com.example.popularnewsapp.ui.popularNews

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.popularnewsapp.R
import com.example.popularnewsapp.databinding.FragmentPopularNewsBinding
import com.example.popularnewsapp.model.NewsModel
import com.example.popularnewsapp.util.Result
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PopularNewsFragment : Fragment(), PopularNewsAdapter.OnItemClickListener {
    private lateinit var binding: FragmentPopularNewsBinding
    private val viewModel: PopularNewsViewModel by viewModels()
    private val popularNewsAdapter = PopularNewsAdapter(this)
    private var articleList = ArrayList<NewsModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_popular_news, container, false
            )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adapter = popularNewsAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()

    }


    private fun setObservers() {
        viewModel.topArticles.observe(viewLifecycleOwner, Observer { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.results.let {
                        Log.e("api success", "true $it")
                        articleList.addAll(it!!)
                        popularNewsAdapter.submitList(it)
                    }
                }

                Result.Status.ERROR -> {
                    Log.e("api success", "error")
                }

                Result.Status.LOADING -> {
                    Log.e("api success", "loading")
                }
            }
        })
    }

    override fun onItemClick(item: NewsModel) {
        val direction = PopularNewsFragmentDirections.actionPopularNewsFragmentToNewsDetailFragment(item)
        findNavController().navigate(direction)
    }
}