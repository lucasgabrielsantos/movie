package br.com.android.movie.intromovie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.android.movie.R
import br.com.android.movie.movielist.HomeActivity
import kotlinx.android.synthetic.main.activity_intro.*
import java.util.*

class IntroActivity : AppCompatActivity() {
    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        introView.setOnClickListener { jump() }
        timer.schedule(object : TimerTask() {
            override fun run() {
                jump()
            }
        }, 3000)
    }

    private fun jump() {
        timer.cancel()
        val intent = Intent(this@IntroActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
