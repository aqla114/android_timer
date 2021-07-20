package com.example.timer.adapters

import com.example.timer.entities.Task
import com.example.timer.models.TaskObject
import com.example.timer.repositories.TaskRepository
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class TaskAdapter : TaskRepository {
    private val realm = Realm.getDefaultInstance()

    override fun push(taskName: String) {
        println("PUSH!!!!!!!")
        realm.executeTransaction { db: Realm ->
            val maxId = db.where<TaskObject>(TaskObject::class.java).max("id")
            val nextId = (maxId?.toLong() ?: 0L) + 1

            println(maxId)

            val task = db.createObject<TaskObject>(nextId)
            task.taskName = taskName
        }

        realm.close()
    }

    override fun get(id: Long): Task {
        println("GET!!!!!!")
        val taskData = realm.where<TaskObject>().equalTo("id", id).findFirst()
        val task = Task(taskData?.id, taskData?.taskName, taskData?.evaluation)

        realm.close()

        return task
    }

    override fun getAll(): List<Task> {
        println("GET ALL!!!")

        println(realm.configuration.path)

        val taskData = realm.where<TaskObject>().findAll()
        val tasks = taskData.map { task ->
            Task(task.id, task.taskName, task.evaluation)
        }

        realm.close()

        return tasks
    }
}
