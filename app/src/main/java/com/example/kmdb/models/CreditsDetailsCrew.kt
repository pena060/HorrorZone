package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

data class CreditsDetailsCrew (
        @SerializedName("crew") val crew_list : List<Crew>
        )
