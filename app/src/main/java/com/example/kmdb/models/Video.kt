package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

data class Video (
        @SerializedName("id") val id : String,
        @SerializedName("name") val videoName : String,
        @SerializedName("key") val key : String
        )