package com.example.kmdb.models

import com.google.gson.annotations.SerializedName

//this will parse through the json query of all the crew members of a specific movie
data class Crew (
        @SerializedName("name") val crew_name : String,
        @SerializedName("profile_path") val crew_profile_path : String?,
        @SerializedName("department") val crew_department: String,
        @SerializedName("job") val crew_job : String
        )