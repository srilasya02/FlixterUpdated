package com.example.flixter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter.R.id

/**
 * [RecyclerView.Adapter] that can display a [MoviesData] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MovieListRecyclerViewAdapter(
    private val movies: List<movieModel>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<MovieListRecyclerViewAdapter.BookViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: movieModel? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(id.movie_description) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(id.movie_image) as ImageView

        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieDescription.text + "'"
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description
        val mov=movie.poster_path

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/$mov")
            .centerInside()
            .into(holder.mMovieImage)
        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return movies.size
    }
}