package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this parses through json query and get the image object of specific movie
data class ImagesQueryResponse (
        @SerializedName("backdrops") val backdrop_list : List<Image>
        )