package com.example.popularnewsapp.repository

import com.example.popularnewsapp.model.ResponseModel
import com.example.popularnewsapp.util.Result
import kotlinx.coroutines.flow.Flow

interface PopularNewsRepository {
    suspend fun getPopularNews(): Flow<Result<ResponseModel>>
}