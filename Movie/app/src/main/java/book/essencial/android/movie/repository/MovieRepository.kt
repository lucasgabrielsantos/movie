package book.essencial.android.movie.repository

import book.essencial.android.movie.service.ServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val serviceApi: ServiceApi) {

    suspend fun getMoviePopularList() =
        withContext(Dispatchers.IO) { serviceApi.getPopularMovies() }

}