package com.example.popularnewsapp.data.remote

import com.example.popularnewsapp.model.ResponseModel
import com.example.popularnewsapp.network.ApiService
import com.example.popularnewsapp.util.ApiConstants
import com.example.popularnewsapp.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import com.example.popularnewsapp.util.Result
import javax.inject.Inject

class PopularNewsRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {


    suspend fun fetchPopularNews(): Result<ResponseModel> {
        val apiService = retrofit.create(ApiService::class.java)
        return getResponse(
            request ={apiService.getMostPopularNews(ApiConstants.API_KEY)},
            defaultErrorMessage = "Error in fetching top stories")

    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
    }
}