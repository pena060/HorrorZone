package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

data class CreditsDetailsCast (
        @SerializedName("cast") val cast_list : List<Cast>
        )