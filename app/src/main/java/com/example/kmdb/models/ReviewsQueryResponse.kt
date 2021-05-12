package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this parses through json query and get the review object of specific movie
data class ReviewsQueryResponse(
        @SerializedName("results") val reviews: List<Review>,
        @SerializedName("page") val page: Int,
        @SerializedName("total_pages") val pages: Int,
        @SerializedName("id") val id : String
)