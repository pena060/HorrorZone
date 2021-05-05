package com.example.kmdb.models

import android.text.format.DateUtils
import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.time.DateTimeException
import java.time.Year
import java.time.YearMonth
import java.util.*

//this class will parse through the movies got from query and independently get their information
data class SpecificMovieDetail(
    @SerializedName("poster_path") val poster_path : String? ,
    @SerializedName("title") val title : String ,
    @SerializedName("runtime") val runtime : Int ? ,
    @SerializedName("release_date") val release_date : 	String,
    @SerializedName("vote_average") val vote_average : Float ,
    @SerializedName("revenue") val revenue : Long ,
    @SerializedName("overview") val overview : String ?,
    @SerializedName("original_language") val original_language : String ,
    @SerializedName("budget") val budget : Long ,
    @SerializedName("tagline") val tagline : String? ,
    @SerializedName("homepage") val homepage : String?,
    @SerializedName("status") val status : String,
    @SerializedName("backdrop_path") val backdrop_path : String?
)