package book.essencial.android.movie.repository

import book.essencial.android.movie.service.ServiceRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository() {

    suspend fun getMoviePopularList() =
        withContext(Dispatchers.IO) { ServiceRetrofit.service.getPopularMovies() }

}