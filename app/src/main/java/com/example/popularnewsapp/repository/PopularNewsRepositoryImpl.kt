package com.example.popularnewsapp.repository

import com.example.popularnewsapp.data.remote.PopularNewsRemoteDataSource
import com.example.popularnewsapp.model.ResponseModel
import com.example.popularnewsapp.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PopularNewsRepositoryImpl @Inject constructor(private val remoteDataSource: PopularNewsRemoteDataSource) :
    PopularNewsRepository {
    override suspend fun getPopularNews(): Flow<Result<ResponseModel>> {
        return flow {
            emit(Result.loading())
            emit(remoteDataSource.fetchPopularNews())
        }.flowOn(Dispatchers.IO)
    }

}