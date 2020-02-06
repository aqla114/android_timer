package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView

const val EXTRA_MESSAGE = "com.example.timer.MESSAGE"

class MainActivity : AppCompatActivity() {
    private val timer = Timer(10000000, 1000)
    private lateinit var textTimer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textTimer = findViewById<TextView>(R.id.timer_text)
    }

    fun start(view: View) {
        timer.initTimer(textTimer)
    }

    fun stop(view: View) {
        timer.cancel()
    }
}

class Timer(millisInFuture: Long, countDownInterval: Long): CountDownTimer(millisInFuture, countDownInterval) {
    private lateinit var textTimer: TextView

    public fun initTimer(textView: TextView) {
        textTimer = textView
        this.start()
    }

    override fun onTick(millisUntilFinished: Long) {
        val secUntilFinished = millisUntilFinished / 1000
        val h = secUntilFinished / 3600
        val m = (secUntilFinished % 3600) / 60
        val s = secUntilFinished % 60
        textTimer.setText("%02d:%02d:%02d".format(h, m, s))
    }

    override fun onFinish() {
        println("finish")
    }
}
