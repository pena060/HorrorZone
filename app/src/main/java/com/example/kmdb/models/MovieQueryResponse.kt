package com.example.kmdb.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


//class will parse through the response given to the query using the TMDB API and save movies into a list
@Parcelize
data class MovieQueryResponse(
    @SerializedName("results")
    val movies : List<Movie>

) : Parcelable{
    constructor() : this(mutableListOf())
}