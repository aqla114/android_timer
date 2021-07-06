package com.example.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.timer.repositories.DataHolderRepository

class CompletionActivity : AppCompatActivity() {
    private val dataHolderRepository: DataHolderRepository<String> = DataHolder()
    private var time = 0
    private var taskName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completion)

        time = intent.getIntExtra(TIME_MESSAGE, 0)
        taskName = intent.getStringExtra(TASK_NAME_MESSAGE)

        val taskNameLabel: TextView = findViewById(R.id.taskNameLabel)

        taskNameLabel.setText(taskName)

    }

    fun backToTimer(button_: View) {
        dataHolderRepository.push(taskName)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}