package com.example.popularnewsapp.model

import java.util.*

data class ResponseModel(
    val status : String,
    val copyright : String,
    val num_results : Int,
    val results : List<NewsModel>
)
