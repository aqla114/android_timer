package com.example.timer

import android.os.CountDownTimer

class Timer: CountDownTimer {
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


fun formatMillisecToTime(millisec: Long): String {
    val secUntilFinished = millisec / 1000
    val h = secUntilFinished / 3600
    val m = (secUntilFinished % 3600) / 60
    val s = secUntilFinished % 60
    return "%02d:%02d:%02d".format(h, m, s)
}