package com.example.kmdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
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
    private lateinit var  SearchMovies : RecyclerView
    private lateinit var  SearchMoviesAdapter : SearchAdapter
    private lateinit var SlayoutManager: GridLayoutManager
    private var Spage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_layout)


        //LayoutManager/Adapter for Now Playing recycler view
        SearchMovies = findViewById(R.id.rv_search)
        SlayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        SearchMovies.layoutManager = SlayoutManager
        SearchMoviesAdapter = SearchAdapter(mutableListOf()) { movie -> goToMovieDetailsActivity(movie) }
        SearchMovies.adapter = SearchMoviesAdapter

        //get Now Playing function
        getSMovies()
    }

    //get Now Playing fetched movies
    private fun onSearchFetched(movies: List<Movie>){
        SearchMoviesAdapter.getHorrorMovies(movies)
        appendSMoviesToScrollListener()
    }

    //adds now playing movies to scroll listener
    private fun appendSMoviesToScrollListener(){
        SearchMovies.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val maxItemsAmount = SlayoutManager.itemCount
                val amountItemsOnScreen = SlayoutManager.childCount
                val firstItem = SlayoutManager.findFirstVisibleItemPosition()

                if(firstItem + amountItemsOnScreen >= maxItemsAmount / 2){
                    SearchMovies.removeOnScrollListener(this)
                    Spage++
                    getSMovies()
                }
            }
        })


    }

    //handle errors
    private fun onError(){
        Toast.makeText(this, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }

    //get Upcoming movies from repository object
    private fun getSMovies(){
        HorrorMovieRepository.SearchMovies(Spage, :: onSearchFetched, :: onError)
    }




    //this function sends the user to the MovieDetailsActivity when they tap on a movie poster
    private fun goToMovieDetailsActivity(movie: Movie){
        val intent = Intent(this, MovieDetailsDisplay::class.java)
        intent.putExtra("id", movie.id)
        this.startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val menuItem = menu!!.findItem(R.id.search_view)

        val searchView = menuItem.actionView as SearchView

        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(filterString: String?): Boolean {
                SearchMoviesAdapter.filter.filter(filterString)
                return true
            }

            override fun onQueryTextChange(filterString: String?): Boolean {
                SearchMoviesAdapter.filter.filter(filterString)
                return true
            }

        })

        return true
    }



}