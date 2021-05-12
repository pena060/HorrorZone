package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this parses through json query and get the cast object
data class CreditsDetailsCast (
        @SerializedName("cast") val cast_list : List<Cast>
        )