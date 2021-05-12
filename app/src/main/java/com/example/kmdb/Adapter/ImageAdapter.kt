package com.example.kmdb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.kmdb.R
import com.example.kmdb.models.Image

//this adapter will be used with the images recycler view
class ImageAdapter (
    private var image: List<Image>
    ) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ImageViewHolder {
            val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.images_item, parent, false)
            return ImageViewHolder(view)
        }
        override fun getItemCount(): Int = image.size
        override fun onBindViewHolder(holder: ImageAdapter.ImageViewHolder, position: Int) {
            holder.bind(image[position])
        }
        fun updateMovies(images: List<Image>) {
            this.image = images
            notifyDataSetChanged()
        }
        inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val poster: ImageView = itemView.findViewById(R.id.movie_image)
            fun bind(image: Image) {
                Glide.with(itemView)
                        .load("https://image.tmdb.org/t/p/w1280${image.image_path}")
                        .fallback(R.mipmap.no_image)
                        .placeholder(R.mipmap.no_image)
                        .error(R.mipmap.no_image_available)
                        .transform(CenterCrop())
                        .into(poster)

            }
        }
}