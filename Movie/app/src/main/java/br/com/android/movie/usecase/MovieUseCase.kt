package br.com.android.movie.usecase

import br.com.android.movie.api.Result
import br.com.android.movie.repository.MovieRepository

class MovieUseCase(private val repository: MovieRepository) {

    suspend fun getPopularMovies(): List<Result> {
        val result = repository.getMoviePopularList().results
        return result

    }
}