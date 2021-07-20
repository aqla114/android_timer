package com.example.timer.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TaskObject : RealmObject() {
    @PrimaryKey
    var id: Long = 0

    var taskName: String = ""
    var evaluation: Long = 1
}