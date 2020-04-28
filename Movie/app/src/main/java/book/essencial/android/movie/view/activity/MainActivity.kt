package book.essencial.android.movie.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import book.essencial.android.movie.R
import book.essencial.android.movie.api.Result
import book.essencial.android.movie.repository.MovieRepository
import book.essencial.android.movie.usecase.MovieUseCase
import book.essencial.android.movie.viewmodel.MovieEvent
import book.essencial.android.movie.viewmodel.MovieViewModel
import book.essencial.android.movie.viewmodel.MovieViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var repository: MovieRepository
    private lateinit var usecase: MovieUseCase
    private lateinit var factory: MovieViewModelFactory
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initObservables()
        initView()
    }


    fun initViewModel() {
        repository = MovieRepository()
        usecase = MovieUseCase(repository)
        factory = MovieViewModelFactory(usecase)
        viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
    }


    fun initObservables() {
        viewModel.eventView.observe(this, Observer { event ->
            event?.let {
                when (event) {
                    is MovieEvent.Success -> createListMovie(event.movies)
                    is MovieEvent.MovieError -> showError(event.error)
                }

            }
        })
    }

    fun showError(error: String) {
        val a = " ERRO"
    }

    fun createListMovie(movieList: List<Result>) {
        val a = ""

    }

    fun initView() {
        viewModel.getMovie()
    }
}


