package com.applications.moviex.recyclerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.applications.moviex.R
import com.applications.moviex.datamodel.Movie
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context, private val movies : List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false)
        Log.d("sunnnn", "inside onCreateViewHolder")
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        Log.d("sunnnn", "movie : ${movies[position]}")
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie : Movie){
            itemView.findViewById<TextView>(R.id.textViewTitle).text=movie.title
            itemView.findViewById<TextView>(R.id.textViewReleaseYear).text=movie.release_date

            Log.d("sunnnn", "movie - url : ${movie.poster_path}")
            // /8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg

            Glide.with(context)
                .load(movie.poster_path)
                .into(itemView.findViewById(R.id.imageViewPoster))
        }

    }

}