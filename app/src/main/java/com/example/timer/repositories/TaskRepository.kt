package com.example.timer.repositories

import com.example.timer.entities.Task

interface TaskRepository {
    fun push(taskName: String)
    fun get(id: Long): Task
    fun getAll(): List<Task>
}