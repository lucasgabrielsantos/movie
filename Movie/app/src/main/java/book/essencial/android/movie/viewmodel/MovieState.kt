package book.essencial.android.movie.viewmodel

import book.essencial.android.movie.api.Result

sealed class MovieState {

    data class LoadingVisible(val visibility: Int) : MovieState()

    data class LoadingGone(val visibility: Int) : MovieState()

    data class Loaded(val movies: List<Result>) : MovieState()

    data class MovieError(val error: String) : MovieState()

    data class MovieClick(val movie: Result) : MovieState()

}