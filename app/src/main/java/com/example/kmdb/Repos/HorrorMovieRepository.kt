package com.example.kmdb.Repos

import android.util.Log
import com.example.kmdb.API.MakeQueryToTMDB
import com.example.kmdb.MovieDetailsCode.movieId
import com.example.kmdb.models.*
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
    fun getNowPlayingMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.getNowPlayingMovies(page = page)
                .enqueue(object : Callback<MovieQueryResponse> {
                    override fun onResponse(call: Call<MovieQueryResponse>, response: Response<MovieQueryResponse>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.movies)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {
                        onError.invoke()

                    }

                })
    }

    //get now playing movies from repository
    fun getUpcomingMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.getUpcomingMovies(page = page)
                .enqueue(object : Callback<MovieQueryResponse> {
                    override fun onResponse(call: Call<MovieQueryResponse>, response: Response<MovieQueryResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.movies)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {
                        onError.invoke()

                    }

                })
    }

    //get popular movies from repository
    fun getPopularMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.getPopularMovies(page = page)
                .enqueue(object : Callback<MovieQueryResponse> {
                    override fun onResponse(call: Call<MovieQueryResponse>, response: Response<MovieQueryResponse>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.movies)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {
                        onError.invoke()

                    }

                })
    }

    //get top rated movies from repository
    fun getTopRatedMovies(page: Int = 1, onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.getTopRatedMovies(page = page)
                .enqueue(object : Callback<MovieQueryResponse> {
                    override fun onResponse(call: Call<MovieQueryResponse>, response: Response<MovieQueryResponse>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.movies)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {
                        onError.invoke()

                    }

                })
    }


    fun getCast(movieId: Int, onSuccess: (cast: List<Cast>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.getMovieCastDetails(id = movieId)
                .enqueue(object : Callback<CreditsDetailsCast> {
                    override fun onResponse(
                            call: Call<CreditsDetailsCast>,
                            response: Response<CreditsDetailsCast>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.cast_list)
                                Log.d("TEST", "Movies: ${responseBody.cast_list}")
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<CreditsDetailsCast>, t: Throwable) {
                        onError.invoke()
                    }
                })

    }

    fun getCrew(movieId: Int, onSuccess: (cast: List<Crew>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.getMovieCrewDetails(id = movieId)
            .enqueue(object : Callback<CreditsDetailsCrew> {
                override fun onResponse(
                    call: Call<CreditsDetailsCrew>,
                    response: Response<CreditsDetailsCrew>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.crew_list)
                            Log.d("TEST", "Movies: ${responseBody.crew_list}")
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<CreditsDetailsCrew>, t: Throwable) {
                    onError.invoke()
                }
            })

    }
}