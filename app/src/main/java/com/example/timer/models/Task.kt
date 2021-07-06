package com.example.timer.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Task : RealmObject() {
    @PrimaryKey
    var id: Long = 0

    var taskName: String = ""
}