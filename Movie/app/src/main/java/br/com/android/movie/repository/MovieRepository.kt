package br.com.android.movie.repository

import br.com.android.movie.service.ServiceRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository() {

    suspend fun getMoviePopularList() =
        withContext(Dispatchers.IO) { ServiceRetrofit.service.getPopularMovies() }

}