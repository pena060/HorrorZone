package com.example.kmdb.Network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kmdb.API.MakeQueryToTMDB
import com.example.kmdb.Network.CurrentNetworkState
import com.example.kmdb.models.Movie
import com.example.kmdb.models.SpecificMovieDetail
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import java.lang.Exception

//this class tries to fetch the movie details and uses the currentNetwork class to handle errors
class MovieDetailsNetworkData (private val apiService: MakeQueryToTMDB, private val compositeDisposable: CompositeDisposable){
    private val _networkState = MutableLiveData<CurrentNetworkState>()
    val networkState : LiveData<CurrentNetworkState>
        get() = _networkState

    private val _downloadMovieDetailsResponse = MutableLiveData<SpecificMovieDetail>()
        val downloadedMovieResponse : LiveData<SpecificMovieDetail>
        get() = _downloadMovieDetailsResponse

    fun fetchMovieDetails(movieId : Int){
        _networkState.postValue(CurrentNetworkState.LOADING)

        try{
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadMovieDetailsResponse.postValue(it)
                            _networkState.postValue(CurrentNetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(CurrentNetworkState.ERROR)
                            it.message?.let { it1 -> Log.e("MovieDetailsNetworkData", it1) }
                        }
                    )
            )
        }
        catch (e: Exception){
            e.message?.let { Log.e("MovieDetailsNetworkData", it) }
        }
    }
}