package br.com.android.movie.movielist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.android.movie.R
import br.com.android.movie.api.Result
import br.com.android.movie.movielistdetails.DetailsMoviesActivity
import br.com.android.movie.repository.MovieRepository
import br.com.android.movie.usecase.MovieUseCase
import br.com.android.movie.viewmodel.MovieViewModel
import br.com.android.movie.viewmodel.MovieViewModelFactory
import br.com.android.movie.viewmodel.event.MovieEvent
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var repository: MovieRepository
    private lateinit var usecase: MovieUseCase
    private lateinit var factory: MovieViewModelFactory
    private lateinit var progressBar: ProgressBar
    private lateinit var showerror: AppCompatTextView
    private lateinit var movieRecyclerView: RecyclerView
    private val movieViewModel: MovieViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViewModel()
        initViews()
    }

    fun initViewModel() {
        movieViewModel.eventView.observe(this, Observer { event ->
            event?.let {
                when (event) {
                    is MovieEvent.Success -> initListMovieAdapter(event.movies)
                    is MovieEvent.LoadingVisible -> loading()
                    is MovieEvent.LoadingGone -> goneLoading()
                    is MovieEvent.MovieError -> showError(event.error)
                    is MovieEvent.MovieClick -> goToDetailsMovie(event.movie)

                }

            }
        })
        movieViewModel.getMovie()
    }

    fun initViews() {
        progressBar = findViewById(R.id.pb_loading)
        movieRecyclerView = findViewById(R.id.recyclerview)

    }

    private fun goToDetailsMovie(movie: Result) {
        val intent = Intent(this, DetailsMoviesActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(MOVIE_KEY, movie)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun initListMovieAdapter(movieList: List<Result>) {
        movieRecyclerView.layoutManager = LinearLayoutManager(this)
        movieRecyclerView.adapter =
            MovieAdapter(
                movieList,
                movieViewModel
            )
        movieRecyclerView.visibility = View.VISIBLE

    }

    private fun loading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun goneLoading() {
        progressBar.visibility = View.GONE
    }

    fun showError(message: String) {
        progressBar.visibility = View.GONE
        Snackbar.make(showerror, message, Snackbar.LENGTH_LONG).show()
    }

    companion object {
        const val MOVIE_KEY = "movie_key"
    }

}


