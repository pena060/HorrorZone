package com.example.kmdb.MovieDetailsCode

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.kmdb.API.MakeQueryToTMDB
import com.example.kmdb.Adapter.HorrorMoviesAdapter
import com.example.kmdb.R
import com.example.kmdb.Repos.MovieDetailsRepo
import com.example.kmdb.models.MovieDetailsViewModel
import com.example.kmdb.models.SpecificMovieDetail
import kotlinx.android.synthetic.main.movie_details.*
import java.text.NumberFormat
import java.util.*

var movieId = 0

class MovieDetailsDisplay : AppCompatActivity() {

    //initialize a viewmodel and repo that will be used in this activity
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var movieRepo : MovieDetailsRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

       movieId = intent.getIntExtra("id", 1)

        val apiService : MakeQueryToTMDB = TheMovieDBClient.getClient()
        movieRepo = MovieDetailsRepo(apiService)

        viewModel = getViewModel(movieId)

        viewModel.movieDetails.observe(this, Observer {
            bindToUI(it)
        })
    }

    fun bindToUI(it: SpecificMovieDetail){
        movie_title_display.text = it.title
        movie_rating.rating = it.vote_average/2f
        movie_length.text = "Runtime: " + it.runtime.toString() + " minutes"
        movie_description.text = it.overview
        release_display.text = it.release_date
        language_display.text = it.original_language
        slogan_display.text = it.tagline
        homepage_link.text = it.homepage
        status_display.text = it.status

        val formatCurrency : NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)
        budget_display.text = formatCurrency.format(it.budget)
        revenue_display.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.poster_path
            Glide.with(this)
                .load(moviePosterURL)
                .transform(CenterCrop())
                .into(poster_image)

        val movieForeGURL = FOREGROUND_BASE_URL + it.backdrop_path
        Glide.with(this)
            .load(movieForeGURL)
            .placeholder(R.mipmap.no_image)
            .error(R.mipmap.no_image)
            .transform(CenterCrop())
            .into(foreground_image)

        Log.d("DisplayActivity", "Movies: $it")
    }

    private fun getViewModel(movieId : Int) : MovieDetailsViewModel{
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailsViewModel(movieRepo, movieId) as T
            }
        })[MovieDetailsViewModel::class.java]
    }

}