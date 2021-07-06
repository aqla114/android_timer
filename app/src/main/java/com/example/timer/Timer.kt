package com.example.timer

import android.os.CountDownTimer



class Timer {
    private val interval: Long
    private val tick: (millisUntilFinished: Long) -> Unit
    private val finish: () -> Unit
    private var millisUntilFinished: Long
    private var timer: InnerTimer?

    constructor(countDownInterval: Long, tickCallback: (millisUntilFinished: Long) -> Unit, finishCallback: () -> Unit) {
        interval = countDownInterval
        tick = tickCallback
        finish = finishCallback
        millisUntilFinished = 0
        timer = null
    }

    fun setTime(millisInFuture: Long) {
        millisUntilFinished = millisInFuture
    }

    fun start() {
        timer = InnerTimer(millisUntilFinished, interval, ::wrappedTick, finish)
        timer?.start()
    }

    fun stop() {
        timer?.cancel()
    }

    fun wrappedTick(millis: Long) {
        millisUntilFinished = millis

        tick(millis)
    }

    inner class InnerTimer: CountDownTimer {
        private val tick: (millisUntilFinished: Long) -> Unit
        private val finish: () -> Unit


        constructor(millisInFuture: Long, countDownInterval: Long, tickCallback: (millisUntilFinished: Long) -> Unit, finishCallback: () -> Unit): super(millisInFuture, countDownInterval) {
            tick = tickCallback
            finish = finishCallback
        }

        override fun onTick(millisUntilFinished: Long) {
            tick(millisUntilFinished)
        }

        override fun onFinish() {
            finish()
        }
    }


}


fun formatMillisecToTime(millisec: Long): String {
    val secUntilFinished = millisec / 1000
    val h = secUntilFinished / 3600
    val m = (secUntilFinished % 3600) / 60
    val s = secUntilFinished % 60
    return "%02d:%02d:%02d".format(h, m, s)
}