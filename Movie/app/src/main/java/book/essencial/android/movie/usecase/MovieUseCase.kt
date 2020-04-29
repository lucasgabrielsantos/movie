package book.essencial.android.movie.usecase

import book.essencial.android.movie.api.Result
import book.essencial.android.movie.repository.MovieRepository

class MovieUseCase(private val repository: MovieRepository) {

    suspend fun getPopularMovies(): List<Result> {
        val result = repository.getMoviePopularList().results
        return result

    }
}