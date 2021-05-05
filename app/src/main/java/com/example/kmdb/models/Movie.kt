package com.example.kmdb.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*


//this class will parse through the movies query and get the info needed for each movie to display on the main page
data class Movie(
    @SerializedName("id") val id : Int ,
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_pages") val pages: Int
    )