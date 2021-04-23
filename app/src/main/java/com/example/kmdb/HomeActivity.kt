package com.example.kmdb

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kmdb.API.HorrorMovieRepository
import com.example.kmdb.Adapter.HorrorMoviesAdapter
import com.example.kmdb.models.Movie


//this activity displays the main homepage for the app displaying horror movies as a list
class HomeActivity : AppCompatActivity(){
    private lateinit var  NowPlayingMovies : RecyclerView
    private lateinit var  NowPlayingMoviesAdapter : HorrorMoviesAdapter
    private lateinit var NPlayoutManager: LinearLayoutManager
    private var NPpage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homeactivitylayout)


        NowPlayingMovies = findViewById(R.id.rv_now_playing)
       NPlayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        NowPlayingMovies.layoutManager = NPlayoutManager


        NowPlayingMoviesAdapter = HorrorMoviesAdapter(mutableListOf())
        NowPlayingMovies.adapter = NowPlayingMoviesAdapter

        getNPMovies()

    }

    //get fetched movies
    private fun onNowPlayingFetched(movies: List<Movie>){
        NowPlayingMoviesAdapter.getHorrorMovies(movies)
        appendNPMoviesToScrollListener()
    }

    //handle errors
    private fun onError(){
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }


    private fun getNPMovies(){
        HorrorMovieRepository.getNowPlayingMovies(NPpage, :: onNowPlayingFetched, :: onError)
    }

    //adds now playing movies to scroll listener
    private fun appendNPMoviesToScrollListener(){
        NowPlayingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val maxItemsAmount = NPlayoutManager.itemCount
                val amountItemsOnScreen = NPlayoutManager.childCount
                val firstItem = NPlayoutManager.findFirstVisibleItemPosition()

                if(firstItem + amountItemsOnScreen >= maxItemsAmount / 2){
                    NowPlayingMovies.removeOnScrollListener(this)
                    NPpage++
                    getNPMovies()
                }
            }
        })
    }

}