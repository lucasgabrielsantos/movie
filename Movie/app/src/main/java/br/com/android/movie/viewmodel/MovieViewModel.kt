package br.com.android.movie.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.android.movie.api.Result
import br.com.android.movie.usecase.MovieUseCase
import br.com.android.movie.viewmodel.event.MovieEvent
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: MovieUseCase) :
    ViewModel() {

    private val event: MutableLiveData<MovieEvent> = MutableLiveData()
    val eventView: LiveData<MovieEvent> = event

    fun getMovie() {
        viewModelScope.launch {
            event.value = MovieEvent.LoadingVisible(View.GONE)
            try {
                val movie = useCase.getPopularMovies()
                event.value = MovieEvent.Success(movie)
                event.value = MovieEvent.LoadingGone(View.GONE)
            } catch (ex: Exception) {
                ex.printStackTrace()
                event.value = MovieEvent.MovieError(ex.message.toString())
            }
        }

    }

    fun clickMovieDetail(movie: Result) {
        event.postValue(MovieEvent.MovieClick(movie))
    }

}