package br.com.android.movie.viewmodel.event

import br.com.android.movie.api.Result

sealed class MovieEvent {

    data class LoadingVisible(val visibility: Int) : MovieEvent()

    data class LoadingGone(val visibility: Int) : MovieEvent()

    data class Success(val movies: List<Result>) : MovieEvent()

    data class MovieError(val error: String) : MovieEvent()

    data class MovieClick(val movie: Result) : MovieEvent()

}