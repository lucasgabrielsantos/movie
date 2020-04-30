package br.com.android.movie.movielistdetails

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.android.movie.R
import br.com.android.movie.api.Result
import br.com.android.movie.movielist.BASE_URL
import br.com.android.movie.movielist.HomeActivity.Companion.MOVIE_KEY
import com.squareup.picasso.Picasso

class DetailsMoviesActivity : AppCompatActivity() {

    private lateinit var imageDetail: ImageView
    private lateinit var imageDetailAlpha: ImageView
    private lateinit var titleDetail: TextView
    private lateinit var description: TextView
    private lateinit var published: TextView
    private lateinit var price: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movies)

        initViews()
        viewsUi()
    }

    fun initViews() {
        imageDetail = findViewById(R.id.imageDetail)
        imageDetailAlpha = findViewById(R.id.imageDetailAlpha)
        titleDetail = findViewById(R.id.titleDetail)
        description = findViewById(R.id.descriptiondetails)
        published = findViewById(R.id.published)
        price = findViewById(R.id.votesMovie)

    }

    private fun viewsUi() {
        if (intent != null && intent.extras != null) {
            val result: Result? = intent.extras?.getParcelable(MOVIE_KEY)
            result?.let {
                titleDetail.text = it.original_title
                description.text = it.overview
                published.text = it.release_date
                price.text = it.vote_average.toString()

                Picasso.with(this)
                    .load(BASE_URL + it.poster_path)
                    .into(imageDetail)
                Picasso.with(this)
                    .load(BASE_URL + it.poster_path)
                    .into(imageDetailAlpha)
            }
        }
    }
}
