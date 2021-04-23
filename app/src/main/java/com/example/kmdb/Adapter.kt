package com.example.kmdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.kmdb.models.Movie
import kotlinx.android.synthetic.main.display_movie_main.view.*

//this is the adapter for the recyler views that will house different movies based on different filters
class Adapter (
    private val movies : List<Movie>
    ) : RecyclerView.Adapter<Adapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.display_movie_main, parent,false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }

        inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"
            fun bindMovie(movie : Movie){

                itemView.movie_title.text = movie.title
                itemView.release.text = movie.release_date

                Glide.with(itemView)
                    .load(IMAGE_BASE + movie.poster_path)
                    .transform(CenterCrop())
                    .into(itemView.movie_poster)

            }
        }

}