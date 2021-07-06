package com.example.timer

import com.example.timer.repositories.DataHolderRepository

class DataHolder : DataHolderRepository<String> {
    private val finishedTasks = mutableListOf<Task>()

    override fun push(taskName: String) {
        finishedTasks.add(Task(taskName))
    }
}

class Task {
    val taskName: String

    constructor(_taskName: String) {
        taskName = _taskName
    }
}