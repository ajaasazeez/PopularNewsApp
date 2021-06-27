package com.example.popularnewsapp.network

import com.example.popularnewsapp.model.ResponseModel
import com.example.popularnewsapp.util.ApiConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET(ApiConstants.MOST_POPULAR)
    suspend fun getMostPopularNews(@Query("api-key") apiKey: String): Response<ResponseModel>
}