package com.example.popularnewsapp.repository

import com.example.popularnewsapp.model.ResponseModel
import com.example.popularnewsapp.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FakePopularNewsRepository:PopularNewsRepository {


    private val responseModel = ResponseModel("200", "copyright", 20, mutableListOf())

    private var shouldReturnNetworkError = false
   /* suspend fun getPopularNews(): Flow<Result<ResponseModel>> {
        return flow {
            emit(Result.loading())
           *//* if (shouldReturnNetworkError)
                emit(Result.error("Error test", null))
            else*//*
                emit(Result.success(responseModel))
        }.flowOn(Dispatchers.IO)
    }*/

    fun shouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getPopularNews(): Flow<Result<ResponseModel>> {
        return flow {
            emit(Result.loading())
            emit(Result.success(responseModel))
        }.flowOn(Dispatchers.IO)
    }
}