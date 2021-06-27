package com.example.popularnewsapp.repository

import com.example.popularnewsapp.data.remote.PopularNewsRemoteDataSource
import com.example.popularnewsapp.model.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.example.popularnewsapp.util.Result
import javax.inject.Inject

class PopularNewsRepo @Inject constructor(private val remoteDataSource: PopularNewsRemoteDataSource) {

    suspend fun getPopularNews(): Flow<Result<ResponseModel>> {
        return flow {
            emit(Result.loading())
            emit(remoteDataSource.fetchPopularNews())
        }.flowOn(Dispatchers.IO)
    }
}