package com.example.popularnewsapp.ui.popularNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.popularnewsapp.databinding.FragmentPopularNewsBinding
import com.example.popularnewsapp.util.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularNewsFragment : Fragment() {
    private lateinit var binding: FragmentPopularNewsBinding
    private val viewModel: PopularNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularNewsBinding.inflate(inflater, container, false)
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

                }

                Result.Status.ERROR -> {

                }

                Result.Status.LOADING -> {

                }
            }

        })
    }
}