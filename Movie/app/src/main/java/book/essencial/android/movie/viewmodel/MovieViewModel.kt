package book.essencial.android.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import book.essencial.android.movie.usecase.MovieUseCase
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: MovieUseCase) :
    ViewModel() {

    private val event: MutableLiveData<MovieEvent> = MutableLiveData()
    val eventView: LiveData<MovieEvent> = event


    fun getMovie() {
        viewModelScope.launch {
            try {
                val movie = useCase.getPopularMovies()
                event.value = MovieEvent.Success(movie)
            } catch (ex: Exception) {
                event.value = MovieEvent.MovieError(ex.message.toString())
            }
        }

    }

}