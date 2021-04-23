package com.example.kmdb.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


//class will get the results which will be parsed into a list, thecurrent page of that list and the total pages
data class MovieQueryResponse(
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val pages: Int,
    )
