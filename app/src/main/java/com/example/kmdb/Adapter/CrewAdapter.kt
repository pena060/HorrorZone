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
import com.example.kmdb.models.Crew

//this adapter will be used with the crew member recycler view
class CrewAdapter (
    private var crew: List<Crew>
) : RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewAdapter.CrewViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cast_list_layout, parent, false)
        return CrewViewHolder(view)
    }
    override fun getItemCount(): Int = crew.size
    override fun onBindViewHolder(holder: CrewAdapter.CrewViewHolder, position: Int) {
        holder.bind(crew[position])
    }
    fun updateMovies(crew: List<Crew>) {
        this.crew = crew
        notifyDataSetChanged()
    }
    inner class CrewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.actor_portrait)
        private val jobName : TextView = itemView.findViewById(R.id.name_character)
        private val crewName : TextView = itemView.findViewById(R.id.actor_name)
        fun bind(crew: Crew) {
            Glide
                    .with(itemView)
                    .load("https://image.tmdb.org/t/p/h632${crew.crew_profile_path}")
                    .fallback(R.drawable.profile_picture_icon_7)
                    .placeholder(R.drawable.profile_picture_icon_7)
                    .error(R.drawable.profile_picture_icon_7)
                    .transform(CenterCrop())
                    .into(poster)


            jobName.text = crew.crew_job
            crewName.text = crew.crew_name
        }
    }
}