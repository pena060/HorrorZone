package com.example.kmdb.API

import com.example.kmdb.models.Movie
import com.example.kmdb.models.MovieQueryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


//This interface is for the specific queries being made
interface MakeQueryToTMDB {

    //Query ofr Now PLaying movies
    @GET("movie/now_playing?with_genres=27")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
        @Query ("page") page : Int
    ): Call<MovieQueryResponse>

    //Query for Top Rated Movies
    @GET("movie/top_rated?with_genres=27")
    fun getTopRatedMovies(
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
            @Query ("page") page : Int
    ): Call<MovieQueryResponse>

    //Query for Upcoming Movies
    @GET("movie/upcoming?with_genres=27")
    fun getUpcomingMovies(
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
            @Query ("page") page : Int
    ): Call<MovieQueryResponse>

    //Query for Popular Movies
    @GET("movie/popular?with_genres=27")
    fun getPopularMovies(
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
            @Query ("page") page : Int
    ): Call<MovieQueryResponse>
}