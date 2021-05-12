package com.example.kmdb.MovieDetailsCode

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.example.kmdb.Adapter.*
import com.example.kmdb.R
import com.example.kmdb.Repos.HorrorMovieRepository
import com.example.kmdb.Repos.MovieDetailsRepo
import com.example.kmdb.models.*
import kotlinx.android.synthetic.main.movie_details.*
import java.text.NumberFormat
import java.util.*

var movieId = 0

class MovieDetailsDisplay : AppCompatActivity() {

    //initialize a viewmodel and repo that will be used in this activity
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var movieRepo : MovieDetailsRepo

    //adapters and recycler view initialization
    private lateinit var movieCast: RecyclerView
    private lateinit var movieCastAdapter: CastAdapter
    private lateinit var movieCrew: RecyclerView
    private lateinit var movieCrewAdapter: CrewAdapter
    private lateinit var movieVid: RecyclerView
    private lateinit var movieVidAdapter: VideoAdapter
    private lateinit var movieImg: RecyclerView
    private lateinit var movieImgAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        //get movieId from HomeActivity
       movieId = intent.getIntExtra("id", 1)


        //get  movie details using apiServices
        val apiService : MakeQueryToTMDB = TheMovieDBClient.getClient()
        movieRepo = MovieDetailsRepo(apiService)
        viewModel = getViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
            bindToUI(it)
        })

        //TO DISPLAY CAST IN RECYCLER VIEW/////////////
        movieCast = findViewById(R.id.rv_cast)
        movieCast.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )

        movieCastAdapter = CastAdapter(listOf())
        movieCast.adapter = movieCastAdapter

        HorrorMovieRepository.getCast(
                movieId,
                onSuccess = ::onCastFetched,
                onError = :: onError
        )

        ///////////////////////////////////////////////

        //TO DISPLAY Crew IN RECYCLER VIEW/////////////
        movieCrew = findViewById(R.id.rv_crew)
        movieCrew.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        movieCrewAdapter = CrewAdapter(listOf())
        movieCrew.adapter = movieCrewAdapter

        HorrorMovieRepository.getCrew(
            movieId,
            onSuccess = ::onCrewFetched,
            onError = :: onError
        )

        ///////////////////////////////////////////////

        //TO DISPLAY VIDEOS IN RECYCLER VIEW/////////////
        movieVid = findViewById(R.id.rv_videos)
        movieVid.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )

        movieVidAdapter = VideoAdapter(listOf())
        movieVid.adapter = movieVidAdapter

        HorrorMovieRepository.getTrailer(
                movieId,
                onSuccess = ::onVidFetched,
                onError = :: onError
        )

        ///////////////////////////////////////////////

        ///////////////////////////////////////////////

        //TO DISPLAY IMAGES IN RECYCLER VIEW/////////////
        movieImg = findViewById(R.id.rv_images)
        movieImg.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        )

        movieImgAdapter = ImageAdapter(listOf())
        movieImg.adapter = movieImgAdapter

        HorrorMovieRepository.getImages(
                movieId,
                onSuccess = ::onImgFetched,
                onError = :: onError
        )

        ///////////////////////////////////////////////


    }

    //display error message when connection is not available
    private fun onError(){
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }

    //bind al;l movie details to the layout ui
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
                    .placeholder(R.mipmap.no_poster)
                    .error(R.mipmap.no_poster)
                    .transform(CenterCrop())
                    .into(poster_image)

        val movieForeGURL = FOREGROUND_BASE_URL + it.backdrop_path
        Glide.with(this)
                .load(movieForeGURL)
                .placeholder(R.mipmap.no_image)
                .error(R.mipmap.no_image)
                .transform(CenterCrop())
                .into(foreground_image)

        Log.d("movie_details", "Movies: $it")
    }

    //fetch cast info
    private fun onCastFetched(cast: List<Cast>) {
        movieCastAdapter.updateMovies(cast)
    }

    //fetch crew info
    private fun onCrewFetched(crew: List<Crew>) {
        movieCrewAdapter.updateMovies(crew)
    }

    //fetch videos info (links)
    private fun onVidFetched(trailers: List<Video>) {
        movieVidAdapter.updateMovies(trailers)
    }

    //fetch images
    private fun onImgFetched(images: List<Image>) {
        movieImgAdapter.updateMovies(images)
    }



    //function used for viewmodel used in movie details
    private fun getViewModel(movieId : Int) : MovieDetailsViewModel{
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailsViewModel(movieRepo, movieId) as T
            }
        })[MovieDetailsViewModel::class.java]
    }

}