package com.example.kmdb.Repos

import androidx.lifecycle.LiveData
import com.example.kmdb.API.MakeQueryToTMDB
import com.example.kmdb.Network.CurrentNetworkState
import com.example.kmdb.Network.MovieDetailsNetworkData
import com.example.kmdb.models.CreditsDetailsCast
import com.example.kmdb.models.CreditsDetailsCrew
import com.example.kmdb.models.Movie
import com.example.kmdb.models.SpecificMovieDetail
import io.reactivex.disposables.CompositeDisposable

//repository where the movie details will be fetched
class MovieDetailsRepo(private val apiService : MakeQueryToTMDB) {
    lateinit var movieDetailsNetworkData: MovieDetailsNetworkData

    fun fetchingMovieDetails(compositeDisposable: CompositeDisposable, movieId : Int): LiveData<SpecificMovieDetail>{
        movieDetailsNetworkData = MovieDetailsNetworkData(apiService,compositeDisposable)
        movieDetailsNetworkData.fetchMovieDetails(movieId)

        return movieDetailsNetworkData.downloadedMovieResponse
    }


    fun getMovieDetailsNetwork(): LiveData<CurrentNetworkState>{
        return movieDetailsNetworkData.networkState
    }
}