package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this parses through json query and get the cast info for a specific movie
data class Cast(
        @SerializedName("name") val cast_name : String,
        @SerializedName("profile_path") val cast_profile_path : String?,
        @SerializedName("character") val cast_character : String,
        @SerializedName("order") val cast_order : Int,

        )