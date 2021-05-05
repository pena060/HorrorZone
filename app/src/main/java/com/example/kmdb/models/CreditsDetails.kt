package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

data class CreditsDetails (
        @SerializedName("cast") val cast : List<Cast>,
        @SerializedName("crew") val crew : List<Crew>
        )