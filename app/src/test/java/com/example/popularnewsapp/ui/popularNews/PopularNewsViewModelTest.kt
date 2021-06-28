package com.example.popularnewsapp.ui.popularNews

import com.example.popularnewsapp.repository.FakePopularNewsRepository
import com.example.popularnewsapp.util.NetworkHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class PopularNewsViewModelTest {

    private lateinit var viewModel:PopularNewsViewModel
    @Inject
    private lateinit var  networkHelper: NetworkHelper

    @Before
    fun setUp(){
        viewModel = PopularNewsViewModel(FakePopularNewsRepository(), networkHelper)
    }

    @Test
    fun get_popular_news(){
        viewModel.getTopArticles()
    }
}