package com.example.popularnewsapp.model

data class ResponseModel(
    val status : String,
    val copyright : String,
    val num_results : Int,
    val results : List<Results>
)
