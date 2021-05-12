package com.example.kmdb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kmdb.R
import com.example.kmdb.models.Video

//this adapter will be used with the videos recycler view
class VideoAdapter (
    private var video: List<Video>
    ) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.VideoViewHolder {
            val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.watch_link_layout, parent, false)
            return VideoViewHolder(view)
        }
        override fun getItemCount(): Int = video.size
        override fun onBindViewHolder(holder: VideoAdapter.VideoViewHolder, position: Int) {
            holder.bind(video[position])
        }
        fun updateMovies(cast: List<Video>) {
            this.video = cast
            notifyDataSetChanged()
        }
        inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val videoLink : TextView = itemView.findViewById(R.id.link_us_watch)
            private val videoName : TextView = itemView.findViewById(R.id.us_header)
            fun bind(video: Video) {
                    videoName.text = video.videoName
                    videoLink.text = "https://www.youtube.com/watch?v=" + video.key
            }
        }
}