package book.essencial.android.movie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import book.essencial.android.movie.R
import book.essencial.android.movie.api.Result
import book.essencial.android.movie.viewmodel.MovieViewModel


class MovieAdapter(
    private val movies: List<Result>,
    private val viewModel: MovieViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.itemmovie_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).onBindMovie(movies[position])
    }
}


