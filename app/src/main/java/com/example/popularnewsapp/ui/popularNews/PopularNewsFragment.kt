package com.example.popularnewsapp.ui.popularNews

import android.os.Bundle
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
import com.example.popularnewsapp.ui.MainActivity
import com.example.popularnewsapp.util.Result
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularNewsFragment : Fragment(), PopularNewsAdapter.OnItemClickListener {
    private lateinit var binding: FragmentPopularNewsBinding
    private val viewModel: PopularNewsViewModel by viewModels()
    private val popularNewsAdapter = PopularNewsAdapter(this)
    private lateinit var mainActivity: MainActivity


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
        mainActivity = activity as MainActivity
        setObservers()

    }


    private fun setObservers() {
        viewModel.topArticles.observe(viewLifecycleOwner, Observer { result ->

            when (result.status) {
                Result.Status.SUCCESS -> {
                    mainActivity.hideLoadingWidget()
                    result.data?.results.let {
                        popularNewsAdapter.submitList(it)
                    }
                }

                Result.Status.ERROR -> {
                    mainActivity.hideLoadingWidget()
                    showSnackBar()
                }

                Result.Status.LOADING -> {
                    mainActivity.showLoadingWidget()
                }
            }
        })
    }

    private fun showSnackBar() {
        val snack = Snackbar.make(
            binding.root,
            getString(R.string.snackbar_msg),
            Snackbar.LENGTH_INDEFINITE
        )
        snack.setAction(getString(R.string.snackbar_action)) {
            viewModel.getTopArticles()
        }
        snack.show()
    }

    override fun onItemClick(item: NewsModel) {
        val direction =
            PopularNewsFragmentDirections.actionPopularNewsFragmentToNewsDetailFragment(item)
        findNavController().navigate(direction)
    }
}