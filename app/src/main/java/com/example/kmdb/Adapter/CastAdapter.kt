package com.example.kmdb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.kmdb.R
import com.example.kmdb.models.Cast
import kotlinx.android.synthetic.main.movie_details.*

//this adapter will be used with the cast member recycler view
class CastAdapter (
    private var cast: List<Cast>
    ) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.CastViewHolder {
            val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.cast_list_layout, parent, false)
            return CastViewHolder(view)
        }
        override fun getItemCount(): Int = cast.size
        override fun onBindViewHolder(holder: CastAdapter.CastViewHolder, position: Int) {
            holder.bind(cast[position])
        }
        fun updateMovies(cast: List<Cast>) {
            this.cast = cast
            notifyDataSetChanged()
        }
        inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val poster: ImageView = itemView.findViewById(R.id.actor_portrait)
            private val characterName : TextView = itemView.findViewById(R.id.name_character)
            private val actorName : TextView = itemView.findViewById(R.id.actor_name)
            fun bind(cast: Cast) {
                Glide
                        .with(itemView)
                        .load("https://image.tmdb.org/t/p/h632${cast.cast_profile_path}")
                        .fallback(R.drawable.profile_picture_icon_7)
                        .placeholder(R.drawable.profile_picture_icon_7)
                        .error(R.drawable.profile_picture_icon_7)
                        .transform(CenterCrop())
                        .into(poster)


                characterName.text = cast.cast_character
                actorName.text = cast.cast_name
            }
        }
}
