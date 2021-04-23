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
    //variables used for Now Playing recycler view
    private lateinit var  NowPlayingMovies : RecyclerView
    private lateinit var  NowPlayingMoviesAdapter : HorrorMoviesAdapter
    private lateinit var NPlayoutManager: LinearLayoutManager
    private var NPpage = 1

    //variables used for Top Rated recycler view
    private lateinit var  TopRatedgMovies : RecyclerView
    private lateinit var  TopRatedMoviesAdapter : HorrorMoviesAdapter
    private lateinit var TPlayoutManager: LinearLayoutManager
    private var TPpage = 1

    //variables used for Upcoming recycler view
    private lateinit var  UpcomingMovies : RecyclerView
    private lateinit var  UpcomingMoviesAdapter : HorrorMoviesAdapter
    private lateinit var UlayoutManager: LinearLayoutManager
    private var Upage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homeactivitylayout)

        //LayoutManager/Adapter for Now Playing recycler view
        NowPlayingMovies = findViewById(R.id.rv_now_playing)
        NPlayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        NowPlayingMovies.layoutManager = NPlayoutManager
        NowPlayingMoviesAdapter = HorrorMoviesAdapter(mutableListOf())
        NowPlayingMovies.adapter = NowPlayingMoviesAdapter

        //get Now Playing function
        getNPMovies()

        //LayoutManager/Adapter for Top Rated recycler view
        TopRatedgMovies = findViewById(R.id.rv_top_rated)
        TPlayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        TopRatedgMovies.layoutManager = TPlayoutManager
        TopRatedMoviesAdapter = HorrorMoviesAdapter(mutableListOf())
        TopRatedgMovies.adapter = TopRatedMoviesAdapter

        //get Top Rated function
        getTPMovies()

        //LayoutManager/Adapter for Upcoming recycler view
        UpcomingMovies = findViewById(R.id.rv_upcoming)
        UlayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        UpcomingMovies.layoutManager = UlayoutManager
        UpcomingMoviesAdapter = HorrorMoviesAdapter(mutableListOf())
        UpcomingMovies.adapter = UpcomingMoviesAdapter

        //get Upcoming function
        getUMovies()

    }

    //get Now Playing fetched movies
    private fun onNowPlayingFetched(movies: List<Movie>){
        NowPlayingMoviesAdapter.getHorrorMovies(movies)
        appendNPMoviesToScrollListener()
    }

    //get Top Rated fetched movies
    private fun onTopRatedFetched(movies: List<Movie>){
        TopRatedMoviesAdapter.getHorrorMovies(movies)
        appendTPMoviesToScrollListener()
    }

    //get Top Rated fetched movies
    private fun onUpcomingFetched(movies: List<Movie>){
        UpcomingMoviesAdapter.getHorrorMovies(movies)
        appendUMoviesToScrollListener()
    }

    //handle errors
    private fun onError(){
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }

    //get top Now Playing from repository object
    private fun getNPMovies(){
        HorrorMovieRepository.getNowPlayingMovies(NPpage, :: onNowPlayingFetched, :: onError)
    }
    //get top rated movies from repository object
    private fun getTPMovies(){
        HorrorMovieRepository.getTopRatedMovies(NPpage, :: onTopRatedFetched, :: onError)
    }
    //get Upcoming movies from repository object
    private fun getUMovies(){
        HorrorMovieRepository.getUpcomingMovies(NPpage, :: onUpcomingFetched, :: onError)
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

    //adds now playing movies to scroll listener
    private fun appendTPMoviesToScrollListener(){
        TopRatedgMovies.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val maxItemsAmount = TPlayoutManager.itemCount
                val amountItemsOnScreen = TPlayoutManager.childCount
                val firstItem = TPlayoutManager.findFirstVisibleItemPosition()

                if(firstItem + amountItemsOnScreen >= maxItemsAmount / 2){
                    TopRatedgMovies.removeOnScrollListener(this)
                    TPpage++
                    getTPMovies()
                }
            }
        })
    }

    //adds upcoming movies to scroll listener
    private fun appendUMoviesToScrollListener(){
        UpcomingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val maxItemsAmount = UlayoutManager.itemCount
                val amountItemsOnScreen = UlayoutManager.childCount
                val firstItem = UlayoutManager.findFirstVisibleItemPosition()

                if(firstItem + amountItemsOnScreen >= maxItemsAmount / 2){
                    UpcomingMovies.removeOnScrollListener(this)
                    Upage++
                    getUMovies()
                }
            }
        })
    }

}