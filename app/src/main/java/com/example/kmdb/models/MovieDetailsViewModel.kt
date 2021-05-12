package com.example.kmdb.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kmdb.Repos.MovieDetailsRepo
import io.reactivex.disposables.CompositeDisposable

//view model used for all movie details
class MovieDetailsViewModel(private val movieRepo : MovieDetailsRepo, movieId : Int): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val movieDetails : LiveData<SpecificMovieDetail> by lazy {
        movieRepo.fetchingMovieDetails(compositeDisposable, movieId)
    }

    //dispose of composite disposable to avoid memory leaks
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}