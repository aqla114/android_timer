package com.example.timer

import android.app.Application
import io.realm.Realm

class TimerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}