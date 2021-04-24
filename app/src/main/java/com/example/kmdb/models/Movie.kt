package com.example.kmdb.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


//this class will parse through the movies got from query and independently get their information
data class Movie(
    @SerializedName("id")
    val id : String ?,
    @SerializedName("poster_path")
    val poster_path : String ?,

)