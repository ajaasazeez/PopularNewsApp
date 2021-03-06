package com.example.popularnewsapp.di

import com.example.popularnewsapp.network.ApiService
import com.example.popularnewsapp.util.ApiConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideBaseUrl() = ApiConstants.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        httpClient: OkHttpClient,
        url: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .client(httpClient)
            .addConverterFactory(converterFactory).build()

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create(GsonBuilder().setLenient().create())

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
        return okHttpClientBuilder.build()
    }

}