package book.essencial.android.movie.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.themoviedb.org/3/"

class ServiceRetrofit {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun service(): ServiceApi = retrofit.create(ServiceApi::class.java)

}


