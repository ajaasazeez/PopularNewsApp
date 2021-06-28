package com.example.popularnewsapp.ui.popularNews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.popularnewsapp.model.ResponseModel
import com.example.popularnewsapp.repository.PopularNewsRepo
import com.example.popularnewsapp.util.NetworkHelper
import com.example.popularnewsapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularNewsViewModel@Inject constructor(private val repository: PopularNewsRepo,
                                              private val networkHelper: NetworkHelper
):ViewModel() {
    val topArticles: MutableLiveData<Result<ResponseModel>> = MutableLiveData()

    init {
        getTopArticles()
    }

    private fun getTopArticles() = viewModelScope.launch {
        if (networkHelper.isNetworkAvailable()) {
            repository.getPopularNews().collect {
                topArticles.postValue(it)
            }
        }
        else{
            topArticles.postValue(Result.error("No internet connection",null))
        }

    }
}