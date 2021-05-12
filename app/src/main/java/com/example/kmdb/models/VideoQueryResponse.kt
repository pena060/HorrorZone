package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this parses through json query and get the video object for a specific movie
data class VideoQueryResponse (
        @SerializedName("results") val videos: List<Video>,
        @SerializedName("id") val id : String
        )