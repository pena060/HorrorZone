package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

data class Review (
        @SerializedName("author") val author_name : String,
        @SerializedName("content") val content : String
        )