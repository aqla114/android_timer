package com.example.timer.repositories

import com.example.timer.entities.Task

interface TaskRepository {
    fun push(task: Task)
    fun get(id: Long): Task
    fun getAll(): List<Task>
    fun cleanDB()
}