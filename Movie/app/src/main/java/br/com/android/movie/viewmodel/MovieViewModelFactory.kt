package br.com.android.movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.android.movie.usecase.MovieUseCase

class MovieViewModelFactory(val useCase: MovieUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieUseCase::class.java)
            .newInstance(useCase)
    }
}