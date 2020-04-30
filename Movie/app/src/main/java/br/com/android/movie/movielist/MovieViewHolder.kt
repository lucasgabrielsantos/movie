package br.com.android.movie.movielist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.android.movie.R
import br.com.android.movie.api.Result
import br.com.android.movie.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso

const val BASE_URL = "https://image.tmdb.org/t/p/w185/"

class MovieViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun onBindMovie(movie: Result, viewModel: MovieViewModel) {
        val imageMovie: ImageView = itemView.findViewById(R.id.imagemovieName)
        Picasso.with(itemView.context)
            .load(BASE_URL + movie.poster_path)
            .into(imageMovie)

        val titleMovie: TextView = itemView.findViewById(R.id.titleNameMovie)
        titleMovie.text = movie.title

        val dateMovie: TextView = itemView.findViewById(R.id.dateMovie)
        dateMovie.text = movie.release_date

        val descriptionMovie: TextView = itemView.findViewById(R.id.PreviDescriptionMovie)
        descriptionMovie.text = movie.overview

        itemView.setOnClickListener { viewModel.clickMovieDetail(movie) }

    }
}

