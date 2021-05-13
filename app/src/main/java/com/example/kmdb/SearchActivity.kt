package com.example.kmdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kmdb.Adapter.SearchAdapter
import com.example.kmdb.MovieDetailsCode.MovieDetailsDisplay
import com.example.kmdb.Repos.HorrorMovieRepository
import com.example.kmdb.models.Movie

class SearchActivity : AppCompatActivity() {

    //variables used for search recycler view
    private lateinit var  AllMovies : RecyclerView
    private lateinit var  AllMoviesAdapter : SearchAdapter
    private lateinit var AlayoutManager: GridLayoutManager
    private var Apage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_layout)

        val back_btn = findViewById<Button>(R.id.btn_back)

        back_btn.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


        //LayoutManager/Adapter for Now Playing recycler view
        AllMovies = findViewById(R.id.rv_search)
        AlayoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        AllMovies.layoutManager = AlayoutManager
        AllMoviesAdapter = SearchAdapter(mutableListOf()) { movie -> goToMovieDetailsActivity(movie) }
        AllMovies.adapter = AllMoviesAdapter

        //get Now Playing function
        getAMovies()
    }

    //get Now Playing fetched movies
    private fun onAllFetched(movies: List<Movie>){
        AllMoviesAdapter.getHorrorMovies(movies)
        appendSMoviesToScrollListener()
    }

    //adds now playing movies to scroll listener
    private fun appendSMoviesToScrollListener(){
        AllMovies.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val maxItemsAmount = AlayoutManager.itemCount
                val amountItemsOnScreen = AlayoutManager.childCount
                val firstItem = AlayoutManager.findFirstVisibleItemPosition()

                if(firstItem + amountItemsOnScreen >= maxItemsAmount / 2){
                    AllMovies.removeOnScrollListener(this)
                    Apage++
                    getAMovies()
                }
            }
        })


    }

    //handle errors
    private fun onError(){
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }

    //get Upcoming movies from repository object
    private fun getAMovies(){
        HorrorMovieRepository.SearchMovies(Apage, :: onAllFetched, :: onError)
    }


    //this function sends the user to the MovieDetailsActivity when they tap on a movie poster
    private fun goToMovieDetailsActivity(movie: Movie){
        val intent = Intent(this, MovieDetailsDisplay::class.java)
        intent.putExtra("id", movie.id)
        this.startActivity(intent)
    }


}