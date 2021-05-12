package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this parses through json query and get the videos for a specific movie
data class Video (
        @SerializedName("id") val id : String,
        @SerializedName("name") val videoName : String,
        @SerializedName("key") val key : String
        )