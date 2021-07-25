package com.example.timer.adapters

import com.example.timer.entities.Task
import com.example.timer.models.TaskObject
import com.example.timer.repositories.TaskRepository
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class TaskAdapter : TaskRepository {
    private val realm = Realm.getDefaultInstance()

    override fun push(task: Task) {
        println("PUSH!!!!!!!")
        realm.executeTransaction { db: Realm ->
            val maxId = db.where<TaskObject>(TaskObject::class.java).max("id")
            val nextId = (maxId?.toLong() ?: 0L) + 1

            val taskObject = db.createObject<TaskObject>(nextId)
            taskObject.taskName = task.taskName
            taskObject.evaluation = task.evaluation
            taskObject.time = task.time
        }

        realm.close()
    }

    override fun get(id: Long): Task {
        println("GET!!!!!!")
        val taskData = realm.where<TaskObject>().equalTo("id", id).findFirst()

        if (taskData?.id == null) {
            println("Task was not found. id = %d".format(id))
            return Task()
        }

        val task = Task(taskData.id, taskData.taskName, taskData.evaluation, taskData.time)

        realm.close()

        return task
    }

    override fun getAll(): List<Task> {
        println("GET ALL!!!")

        val taskData = realm.where<TaskObject>().findAll()
        val tasks = taskData.map { task ->
            Task(task.id, task.taskName, task.evaluation, task.time)
        }

        realm.close()

        return tasks
    }

    override fun cleanDB() {
        realm.executeTransaction { db ->
            db.deleteAll()
        }
    }
}
