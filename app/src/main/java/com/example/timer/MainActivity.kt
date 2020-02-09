package com.example.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView

const val EXTRA_MESSAGE = "com.example.timer.MESSAGE"

const val COUNTDOWN_INTERVAL: Long = 1000

class MainActivity : AppCompatActivity() {
    private lateinit var timerText: TextView
    private lateinit var taskMinutesInput: EditText
    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timer_text)
        taskMinutesInput = findViewById(R.id.task_minutes_text_edit)
        taskMinutesInput.addTextChangedListener(MyTextWatcher(timerText))
    }

    fun start(button: View) {
        val minutes = (taskMinutesInput.text.toString().toLongOrNull() ?: 0) * 60 * 100
        timer = Timer(minutes, COUNTDOWN_INTERVAL, ::tick, ::finishTimer)
        timer.start()
        taskMinutesInput.setEnabled(false)
    }

    fun stop(button: View) {
        timer.cancel()
        taskMinutesInput.setEnabled(true)
    }

    fun tick(millisec: Long) {
        println(millisec)
        timerText.setText(formatMillisecToTime(millisec))
    }

    fun finishTimer() {
        println("finish")
        val intent = Intent(this, CompletionActivity::class.java)
        startActivity(intent)
    }
}

class MyTextWatcher(textView: TextView): TextWatcher {
    private val textView = textView

    override fun afterTextChanged(s: Editable) {
        val millisec = (s.toString().toLongOrNull() ?: 0) * 60 * 1000
        textView.setText(formatMillisecToTime(millisec))
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}
