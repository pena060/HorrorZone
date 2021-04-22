package com.example.kmdb.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


//this class will parse through the movies got from query and independently get their information
@Parcelize
data class   Movie(
    @SerializedName("id")
    val id : String ?,
    @SerializedName("title")
    val title : String ?,
    @SerializedName("poster_path")
    val poster_path : String ?,
    @SerializedName("release_date")
    val release_date : String ?



) : Parcelable{
    constructor(): this("","","","")
}