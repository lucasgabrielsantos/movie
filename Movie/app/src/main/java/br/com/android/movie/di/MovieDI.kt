package br.com.android.movie.di

import br.com.android.movie.repository.MovieRepository
import br.com.android.movie.service.ServiceRetrofit
import br.com.android.movie.usecase.MovieUseCase
import br.com.android.movie.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object MovieDI {
    val viewModelModule = module {
        viewModel { MovieViewModel(useCase = get()) }

        factory { MovieUseCase(repository = get()) }
        factory { MovieRepository() }
        single { ServiceRetrofit() }
    }


    fun init() = loadKoinModules(viewModelModule)

}