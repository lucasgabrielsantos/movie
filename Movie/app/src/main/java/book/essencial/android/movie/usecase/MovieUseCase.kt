package book.essencial.android.movie.usecase

import book.essencial.android.movie.api.Result
import book.essencial.android.movie.repository.MovieRepository

class MovieUseCase(private val repository: MovieRepository) {

    suspend fun getPopularMovies(): List<Result> {
        val result = repository.getMoviePopularList().results
        return result
        val popularMovie = ArrayList<Result>()
        result.forEach { resultPopularMovie ->
            popularMovie.add(
                Result(
                    resultPopularMovie.adult,
                    resultPopularMovie.backdrop_path,
                    resultPopularMovie.genre_ids,
                    resultPopularMovie.id,
                    resultPopularMovie.original_language,
                    resultPopularMovie.original_title,
                    resultPopularMovie.overview,
                    resultPopularMovie.popularity,
                    resultPopularMovie.poster_path,
                    resultPopularMovie.release_date,
                    resultPopularMovie.title,
                    resultPopularMovie.video,
                    resultPopularMovie.vote_average,
                    resultPopularMovie.vote_count
                )
            )
        }
        return popularMovie
    }
}