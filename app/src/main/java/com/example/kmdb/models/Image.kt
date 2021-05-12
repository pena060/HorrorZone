package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

data class Image (
        @SerializedName("file_path") val image_path : String,
        )