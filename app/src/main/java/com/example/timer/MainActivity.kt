package com.example.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.timer.adapters.TaskAdapter
import com.example.timer.repositories.TaskRepository

const val TIME_MESSAGE = "com.example.timer.TIME_MESSAGE"
const val TASK_NAME_MESSAGE = "com.example.timer.TASK_NAME_MESSAGE"

const val COUNTDOWN_INTERVAL: Long = 1000

class MainActivity : AppCompatActivity() {
    private lateinit var timerText: TextView
    private lateinit var taskMinutesInput: EditText
    private lateinit var taskNameInput: EditText
    private lateinit var timer: Timer
    private lateinit var startButton: Button
    private lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timer_text)
        taskMinutesInput = findViewById(R.id.task_minutes_text_edit)
        taskNameInput = findViewById(R.id.taskNameEditText)
        timer = Timer(COUNTDOWN_INTERVAL, ::tick, ::finishTimer)
        taskMinutesInput.addTextChangedListener(MyTextWatcher(timerText, timer))
        startButton = findViewById(R.id.start_button)

        taskRepository = TaskAdapter()

        val tasks = taskRepository.getAll()

        println(tasks)
    }

    fun start(button: View) {
        timer.start()

        taskMinutesInput.setEnabled(false)
        startButton.setEnabled(false)
    }

    fun stop(button_: View) {
        timer.stop()
        taskMinutesInput.setEnabled(true)
        startButton.setEnabled(true)
    }

    fun tick(millisec: Long) {
        timerText.setText(formatMillisecToTime(millisec))
    }

    fun finishTimer() {
        val finishedMinutes = Integer.parseInt(taskMinutesInput.text.toString())
        val taskName = taskNameInput.text.toString()

        val intent = Intent(this, CompletionActivity::class.java).apply {
            putExtra(TIME_MESSAGE, finishedMinutes)
            putExtra(TASK_NAME_MESSAGE, taskName)
        }

        startActivity(intent)
    }
}

class MyTextWatcher(textView: TextView, timer: Timer): TextWatcher {
    private val textView = textView
    private val timer = timer

    override fun afterTextChanged(s: Editable) {
        val millisec = (s.toString().toLongOrNull() ?: 0) * 60 * 100
        timer.setTime(millisec)
        textView.setText(formatMillisecToTime(millisec))
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}
