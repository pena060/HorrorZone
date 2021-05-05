package com.example.kmdb.Repos

import com.example.kmdb.API.MakeQueryToTMDB
import com.example.kmdb.MovieDetailsCode.movieId
import com.example.kmdb.models.Movie
import com.example.kmdb.models.MovieQueryResponse
import com.example.kmdb.models.MovieTrailer
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.crypto.Cipher.SECRET_KEY


object HorrorMovieRepository {

    private val makeQueryToTMDB: MakeQueryToTMDB

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        makeQueryToTMDB = retrofit.create(MakeQueryToTMDB::class.java)
    }

    //get now playing movies from repository
    fun getNowPlayingMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        makeQueryToTMDB.getNowPlayingMovies(page = page)
            .enqueue(object : Callback<MovieQueryResponse> {
                override fun onResponse(call: Call<MovieQueryResponse>, response: Response<MovieQueryResponse>) {
                    if (response.isSuccessful){
                        val responseBody = response.body()

                        if(responseBody != null){
                            onSuccess.invoke(responseBody.movies)
                        }else{
                            onError.invoke()
                        }
                    }else{
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {
                    onError.invoke()

                }

            })
    }

    //get now playing movies from repository
    fun getUpcomingMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        makeQueryToTMDB.getUpcomingMovies(page = page)
                .enqueue(object : Callback<MovieQueryResponse> {
                    override fun onResponse(call: Call<MovieQueryResponse>, response: Response<MovieQueryResponse>
                    ) {
                        if (response.isSuccessful){
                            val responseBody = response.body()

                            if(responseBody != null){
                                onSuccess.invoke(responseBody.movies)
                            }else{
                                onError.invoke()
                            }
                        }else{
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {
                        onError.invoke()

                    }

                })
    }

    //get popular movies from repository
    fun getPopularMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        makeQueryToTMDB.getPopularMovies(page = page)
                .enqueue(object : Callback<MovieQueryResponse> {
                    override fun onResponse(call: Call<MovieQueryResponse>, response: Response<MovieQueryResponse>) {
                        if (response.isSuccessful){
                            val responseBody = response.body()

                            if(responseBody != null){
                                onSuccess.invoke(responseBody.movies)
                            }else{
                                onError.invoke()
                            }
                        }else{
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {
                        onError.invoke()

                    }

                })
    }

    //get top rated movies from repository
    fun getTopRatedMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit){
        makeQueryToTMDB.getTopRatedMovies(page = page)
                .enqueue(object : Callback<MovieQueryResponse> {
                    override fun onResponse(call: Call<MovieQueryResponse>, response: Response<MovieQueryResponse>) {
                        if (response.isSuccessful){
                            val responseBody = response.body()

                            if(responseBody != null){
                                onSuccess.invoke(responseBody.movies)
                            }else{
                                onError.invoke()
                            }
                        }else{
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {
                        onError.invoke()

                    }

                })
    }

}