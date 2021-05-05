package com.example.kmdb.API
import com.example.kmdb.MovieDetailsCode.movieId
import com.example.kmdb.models.CreditsDetails
import com.example.kmdb.models.MovieQueryResponse
import com.example.kmdb.models.SpecificMovieDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.Single


//This interface is for the specific queries being made
interface MakeQueryToTMDB {

    //Query for Now Playing movies
    @GET("movie/now_playing?with_genres=27&append_to_response=credits")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
        @Query ("page") page : Int
    ): Call<MovieQueryResponse>

    //Query for Top Rated Movies
    @GET("movie/top_rated?with_genres=27&append_to_response=credits")
    fun getTopRatedMovies(
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
            @Query ("page") page : Int
    ): Call<MovieQueryResponse>

    //Query for Upcoming Movies
    @GET("movie/upcoming?with_genres=27&append_to_response=credits")
    fun getUpcomingMovies(
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
            @Query ("page") page : Int
    ): Call<MovieQueryResponse>

    //Query for Popular Movies
    @GET("movie/popular?with_genres=27&append_to_response=credits")
    fun getPopularMovies(
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
            @Query ("page") page : Int
    ): Call<MovieQueryResponse>


    //get single movie details
    @GET("movie/{movie_id}?with_genres=27&append_to_response=credits")
    fun  getMovieDetails(
            @Path("movie_id") id: Int
    ): Single<SpecificMovieDetail>

    //get credits info from specific movie
    @GET("movie/{movie_id}?with_genres=27&append_to_response=credits")
    fun  getMovieCreditDetails(
            @Path("movie_id") id: Int
    ): Single<CreditsDetails>

    //search for movies
    @GET("search/movie?with_genres=27")
    fun  getSearchMovie(
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
            @Query ("query") query : String,
            @Query ("page") page : Int
    ): Call<MovieQueryResponse>


}