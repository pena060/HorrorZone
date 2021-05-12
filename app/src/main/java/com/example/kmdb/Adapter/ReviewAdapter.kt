package com.example.kmdb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kmdb.R
import com.example.kmdb.models.Review

class ReviewAdapter (
    private var reviews: List<Review>
    ) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.ReviewViewHolder {
            val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.reviews_item, parent, false)
            return ReviewViewHolder(view)
        }
        override fun getItemCount(): Int = reviews.size
        override fun onBindViewHolder(holder: ReviewAdapter.ReviewViewHolder, position: Int) {
            holder.bind(reviews[position])
        }
        fun updateMovies(reviews: List<Review>) {
            this.reviews = reviews
            notifyDataSetChanged()
        }
        inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val reviewAuthor : TextView = itemView.findViewById(R.id.author_name)
            private val reviewContent : TextView = itemView.findViewById(R.id.rev_content)
            fun bind(review: Review) {
                reviewAuthor.text = review.author_name
                reviewContent.text = review.content

            }
        }
}