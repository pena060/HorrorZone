package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

data class ImagesQueryResponse (
        @SerializedName("backdrops") val backdrop_list : List<Image>
        )