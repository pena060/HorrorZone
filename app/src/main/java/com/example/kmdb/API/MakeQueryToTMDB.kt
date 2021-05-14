package com.example.kmdb.API
import com.example.kmdb.MovieDetailsCode.movieId
import com.example.kmdb.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import io.reactivex.Single


//This interface is for the specific queries being made
interface MakeQueryToTMDB {

    //Query for Now Playing movies
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


    //get single movie details
    @GET("movie/{movie_id}?with_genres=27")
    fun  getMovieDetails(
            @Path("movie_id") id: Int
    ): Single<SpecificMovieDetail>

    //get cast info from specific movie
    @GET("movie/{movie_id}/credits?with_genres=27")
    fun  getMovieCastDetails(
            @Path("movie_id") id: Int,
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f"
    ): Call<CreditsDetailsCast>

    //get crew info from specific movie
    @GET("movie/{movie_id}/credits?with_genres=27")
    fun  getMovieCrewDetails(
            @Path("movie_id") id: Int,
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f"
    ): Call<CreditsDetailsCrew>


    //get video link(s) for specific movie
    @GET("movie/{movie_id}/videos?")
    fun  getVideo(
            @Path("movie_id") id: Int,
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f"
    ): Call<VideoQueryResponse>

    //get video link(s) for specific movie
    @GET("movie/{movie_id}/images?")
    fun  getImages(
            @Path("movie_id") id: Int,
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f"
    ): Call<ImagesQueryResponse>

    //get Reviews for specific movie
    @GET("movie/{movie_id}/reviews?")
    fun  getReviews(
            @Path("movie_id") id: Int,
            @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
            @Query ("page") page : Int
    ): Call<ReviewsQueryResponse>

    //get all movies
    @GET("discover/movie?&with_genres=27&without_genres=10751")
    fun  Search(
        @Query("api_key") apiKey: String = "28d979e940b6fe72b65e85d6eb5ea77f",
        @Query ("page") page : Int
    ): Call<MovieQueryResponse>


}