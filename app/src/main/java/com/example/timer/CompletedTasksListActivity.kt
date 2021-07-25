package com.example.timer

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timer.adapters.CompletedTasksListAdapter
import com.example.timer.adapters.TaskAdapter
import com.example.timer.repositories.TaskRepository

import kotlinx.android.synthetic.main.activity_completed_tasks_list.*
import kotlinx.android.synthetic.main.content_completed_tasks_list.*

class CompletedTasksListActivity : AppCompatActivity() {
    private val taskRepository: TaskRepository = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_tasks_list)
        setSupportActionBar(toolbar)

        val tasks = taskRepository.getAll()

        completed_tasks_list.layoutManager = LinearLayoutManager(this)
        completed_tasks_list.adapter = CompletedTasksListAdapter(tasks)
    }

    fun createTask(button: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
