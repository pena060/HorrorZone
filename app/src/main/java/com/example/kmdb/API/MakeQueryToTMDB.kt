package com.example.kmdb.API

import com.example.kmdb.models.MovieQueryResponse
import retrofit2.Call
import retrofit2.http.GET

//This interface is for the specific query being made (Top Rated Horror)
interface MakeQueryToTMDB {
    @GET("/3/movie/top_rated?api_key=28d979e940b6fe72b65e85d6eb5ea77f&with_genres=27")
    fun getHorrorMovies(): Call<MovieQueryResponse>


}