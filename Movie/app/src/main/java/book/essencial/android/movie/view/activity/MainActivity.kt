package book.essencial.android.movie.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import book.essencial.android.movie.R
import book.essencial.android.movie.api.Result
import book.essencial.android.movie.repository.MovieRepository
import book.essencial.android.movie.usecase.MovieUseCase
import book.essencial.android.movie.view.adapter.MovieAdapter
import book.essencial.android.movie.viewmodel.MovieEvent
import book.essencial.android.movie.viewmodel.MovieViewModel
import book.essencial.android.movie.viewmodel.MovieViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var repository: MovieRepository
    private lateinit var usecase: MovieUseCase
    private lateinit var factory: MovieViewModelFactory
    private lateinit var movieviewModel: MovieViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var showerror: AppCompatTextView
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private lateinit var imageView: ImageView
    private lateinit var titlemovie: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initObservables()
        initViews()
    }


    fun initViewModel() {
        repository = MovieRepository()
        usecase = MovieUseCase(repository)
        factory = MovieViewModelFactory(usecase)
        movieviewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
        movieviewModel.getMovie()
    }


    fun initObservables() {
        movieviewModel.eventView.observe(this, Observer { event ->
            event?.let {
                when (event) {
                    is MovieEvent.Success -> initListMovieAdapter(event.movies)
                    is MovieEvent.LoadingVisible -> loading()
                    is MovieEvent.MovieError -> showError(event.error)
                }

            }
        })
    }

    fun initListMovieAdapter(movieList: List<Result>) {
        movieRecyclerView.layoutManager = LinearLayoutManager(this)
        movieRecyclerView.adapter = MovieAdapter(movieList, movieviewModel)
        movieRecyclerView.visibility = View.VISIBLE

    }

    fun initViews() {
        progressBar = findViewById(R.id.pb_loading)
        movieRecyclerView = findViewById(R.id.recyclerview)


    }

    private fun loading() {
        progressBar.visibility = View.VISIBLE
    }

    fun showError(message: String) {
        progressBar.visibility = View.GONE
        Snackbar.make(showerror, message, Snackbar.LENGTH_LONG).show()
    }


}


