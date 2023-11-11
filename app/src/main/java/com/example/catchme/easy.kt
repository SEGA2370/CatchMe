package com.example.catchme

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.catchme.databinding.ActivityEasyBinding
import kotlin.random.Random

class easy : AppCompatActivity() {
    private lateinit var binding: ActivityEasyBinding
    private var score: Int = 0
    private var imagePosition = ArrayList<ImageView>()
    private val handler = Handler()
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEasyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        score = 0
        imagePosition = arrayListOf(
            binding.imageView1, binding.imageView2, binding.imageView3,
            binding.imageView4, binding.imageView5, binding.imageView6,
            binding.imageView7, binding.imageView8, binding.imageView9,
            binding.imageView10, binding.imageView11, binding.imageView12
        )

        makeInvisible()
        object : CountDownTimer(50000, 1000) {
            override fun onFinish() {
                binding.timeid.text = "GAME OVER!!"
                handler.removeCallbacks(runnable)
                for (img in imagePosition) {
                    img.visibility = View.INVISIBLE
                }
            }

            override fun onTick(p0: Long) {
                binding.timeid.text = "TIMER: " + p0 / 1000
            }
        }.start()
    }

    private fun makeInvisible() {
        runnable = object : Runnable {
            override fun run() {
                for (img in imagePosition) {
                    img.visibility = View.INVISIBLE
                }
                val curIndex = Random.nextInt(11 - 0)
                imagePosition[curIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View) {
        score += 15
        binding.scoreid.text = "SCORE: $score"
    }

    fun restart(view: View) {
        score = 0
        binding.scoreid.text = "SCORE: 0"
        binding.timeid.text = "TIME: 50"
        finish()
        startActivity(intent)
    }

    fun reachMenu(view: View) {
        val button = findViewById<Button>(R.id.button4)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}