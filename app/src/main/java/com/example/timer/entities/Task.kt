package com.example.timer.entities

class Task {
    val id: Long
    val taskName: String
    val evaluation: Long

    constructor(_id: Long?, _taskName: String?, _evaluation: Long?) {
        id = _id ?: 0
        taskName = _taskName ?: ""
        evaluation = _evaluation ?: 1
    }
}