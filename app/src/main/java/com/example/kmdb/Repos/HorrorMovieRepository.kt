package com.example.kmdb.Repos

import android.util.Log
import com.example.kmdb.API.MakeQueryToTMDB
import com.example.kmdb.models.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

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

    //get upcoming movies from repository
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


    //get cast from repository
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

    //get crew from repository
    fun getCrew(movieId: Int, onSuccess: (crew: List<Crew>) -> Unit, onError: () -> Unit) {
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

    //get trailer/videos from repository
    fun getTrailer(movieId: Int, onSuccess: (video: List<Video>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.getVideo(id = movieId)
                .enqueue(object : Callback<VideoQueryResponse> {
                    override fun onResponse(
                            call: Call<VideoQueryResponse>,
                            response: Response<VideoQueryResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.videos)
                                Log.d("TEST", "Movies: ${responseBody.videos}")
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<VideoQueryResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })

    }

    //get images from repository
    fun getImages(movieId: Int, onSuccess: (image: List<Image>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.getImages(id = movieId)
                .enqueue(object : Callback<ImagesQueryResponse> {
                    override fun onResponse(
                            call: Call<ImagesQueryResponse>,
                            response: Response<ImagesQueryResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.backdrop_list)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<ImagesQueryResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })

    }

    //get reviews from repository
    fun getReviews(movieId: Int, page: Int = 1,onSuccess: (reviews: List<Review>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.getReviews(id = movieId, page = page)
                .enqueue(object : Callback<ReviewsQueryResponse> {
                    override fun onResponse(
                            call: Call<ReviewsQueryResponse>,
                            response: Response<ReviewsQueryResponse>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            if (responseBody != null) {
                                onSuccess.invoke(responseBody.reviews)
                            } else {
                                onError.invoke()
                            }
                        } else {
                            onError.invoke()
                        }
                    }

                    override fun onFailure(call: Call<ReviewsQueryResponse>, t: Throwable) {
                        onError.invoke()
                    }
                })

    }
    //get all movies/search from repository
    fun SearchMovies(page: Int = 1,onSuccess: (movies: List<Movie>) -> Unit, onError: () -> Unit) {
        makeQueryToTMDB.Search(page = page)
            .enqueue(object : Callback<MovieQueryResponse> {
                override fun onResponse(
                    call: Call<MovieQueryResponse>,
                    response: Response<MovieQueryResponse>
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

}