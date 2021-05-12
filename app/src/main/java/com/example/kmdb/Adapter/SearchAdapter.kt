package com.example.kmdb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.kmdb.R
import com.example.kmdb.models.Movie

class SearchAdapter (
    private var movies: MutableList<Movie>,
    private val movieClicked: (movie : Movie) -> Unit
    ) : RecyclerView.Adapter<SearchAdapter.MovieViewHolder>(), Filterable{
    var itemModelListFilter = ArrayList<Movie>()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MovieViewHolder{
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.display_movie_main, parent, false)
            return MovieViewHolder(view)
        }
        //standard view holder code (needed to get recycler working properly)
        override fun onBindViewHolder(holder: SearchAdapter.MovieViewHolder, position: Int) {
            holder.bindMoviesToAdapter(movies[position])
        }

        //will be used to get movies into the recycler view using adapter in the home activity
        fun getHorrorMovies(movies: List<Movie>){
            this.movies.addAll(movies)
            this.itemModelListFilter.addAll(movies)
            notifyDataSetChanged()
            notifyItemRangeInserted(this.movies.size, movies.size - 1)
        }



        //this class will be used to display info needed for each movie in the home activity
        inner class MovieViewHolder(item: View) : RecyclerView.ViewHolder(item){
            private val poster : ImageView = itemView.findViewById(R.id.movie_poster)

            fun bindMoviesToAdapter(movie: Movie){
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w342${movie.poster_path}")
                    .placeholder(R.mipmap.no_poster)
                    .error(R.mipmap.no_poster)
                    .transform(CenterCrop())
                    .into(poster)
                //invoke model class movie when movie poster is clicked
                itemView.setOnClickListener { movieClicked.invoke(movie) }
            }
        }

        //amount of results that will be displayed
        override fun getItemCount(): Int = movies.size



    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(charsequence: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if(charsequence == null || charsequence.length < 0){
                    filterResults.count = itemModelListFilter.size
                    filterResults.values = itemModelListFilter
                }else{
                    var searchChr =  charsequence.toString()
                    val itemModel = ArrayList<Movie>()
                    for (item in itemModelListFilter){
                        if(item.name.contains(searchChr)){
                            itemModel.add(item)
                        }
                    }
                    filterResults.count = itemModel.size
                    filterResults.values = itemModel
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
                if (filterResults != null) {
                    movies = filterResults.values as MutableList<Movie>
                }
                notifyDataSetChanged()
            }

        }
    }

}