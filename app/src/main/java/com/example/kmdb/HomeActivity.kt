package com.example.kmdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kmdb.API.MakeQueryToTMDB
import com.example.kmdb.API.MovieAPIService
import com.example.kmdb.models.Movie
import com.example.kmdb.models.MovieQueryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//this activity displays the main homepage for the app displaying horror movies as a list
class HomeActivity : AppCompatActivity(){
    var lManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    //var lManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homeactivitylayout)



        val rv : RecyclerView = findViewById(R.id.rv_top_rated)

        rv.layoutManager = lManager
        rv.setHasFixedSize(true)
        getNowPlayingMovies { movies: List<Movie> -> rv.adapter = Adapter(movies)}



    }
    //get the movies from the query results
    private fun getMovies(callback: (List<Movie>) -> Unit){
        val apiService = MovieAPIService.getInstance().create(MakeQueryToTMDB::class.java)
        apiService.getTopMovies().enqueue(object : Callback<MovieQueryResponse> {
            override fun onResponse(
                call: Call<MovieQueryResponse>,
                response: Response<MovieQueryResponse>
            ) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {

            }

        })

    }

    private fun getNowPlayingMovies(callback: (List<Movie>) -> Unit){
        val apiService = MovieAPIService.getInstance().create(MakeQueryToTMDB::class.java)
        apiService.getNowPlayingMovies().enqueue(object : Callback<MovieQueryResponse> {
            override fun onResponse(
                    call: Call<MovieQueryResponse>,
                    response: Response<MovieQueryResponse>
            ) {
                return callback(response.body()!!.movies)
            }

            override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {

            }

        })

    }


}