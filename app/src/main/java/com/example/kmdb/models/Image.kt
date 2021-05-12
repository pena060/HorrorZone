package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this parses through json query and get the images for a specific movie
data class Image (
        @SerializedName("file_path") val image_path : String,
        )