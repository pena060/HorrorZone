package com.example.kmdb.API

import com.example.kmdb.models.MovieQueryResponse
import retrofit2.Call
import retrofit2.http.GET

//This interface is for the specific queries being made
interface MakeQueryToTMDB {
    @GET("/3/movie/top_rated?api_key=28d979e940b6fe72b65e85d6eb5ea77f&with_genres=27")
    fun getTopMovies(): Call<MovieQueryResponse>

    @GET("/3/movie/now_playing?api_key=28d979e940b6fe72b65e85d6eb5ea77f&with_genres=27")
    fun getNowPlayingMovies(): Call<MovieQueryResponse>


}