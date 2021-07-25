package com.example.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.timer.adapters.TaskAdapter
import com.example.timer.entities.Task
import com.example.timer.repositories.TaskRepository

class CompletionActivity : AppCompatActivity() {
    private val taskRepository: TaskRepository = TaskAdapter()

    private var time : Long = 0
    private var taskName = ""
    private var evaluation : Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completion)

        time = intent.getLongExtra(TIME_MESSAGE, 0)
        taskName = intent.getStringExtra(TASK_NAME_MESSAGE)

        val taskNameLabel: TextView = findViewById(R.id.taskNameLabel)

        taskNameLabel.text = taskName

        val radioGroup = findViewById<RadioGroup>(R.id.evaluationRadioGroup)
        radioGroup.setOnCheckedChangeListener { radioGroup: RadioGroup, checkedId: Int -> updateEvaluation(radioGroup, checkedId) }
    }

    fun backToTimer(button_: View) {
        val task = Task(taskName = taskName, evaluation = evaluation, time = time)
        taskRepository.push(task)

        val intent = Intent(this, CompletedTasksListActivity::class.java)
        startActivity(intent)
    }

    private fun updateEvaluation(radioGroup: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.evaluation1 -> evaluation = 1
            R.id.evaluation2 -> evaluation = 2
            R.id.evaluation3 -> evaluation = 3
            R.id.evaluation4 -> evaluation = 4
            R.id.evaluation5 -> evaluation = 5
        }
    }
}