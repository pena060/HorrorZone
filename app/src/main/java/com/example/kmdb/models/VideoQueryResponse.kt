package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

data class VideoQueryResponse (
        @SerializedName("results") val videos: List<Video>,
        @SerializedName("id") val id : String
        )