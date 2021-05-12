package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this parses through json query and get the reviews for a specific movie
data class Review (
        @SerializedName("author") val author_name : String,
        @SerializedName("content") val content : String
        )