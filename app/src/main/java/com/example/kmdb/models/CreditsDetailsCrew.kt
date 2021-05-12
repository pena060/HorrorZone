package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this parses through json query and get the crew object
data class CreditsDetailsCrew (
        @SerializedName("crew") val crew_list : List<Crew>
        )
