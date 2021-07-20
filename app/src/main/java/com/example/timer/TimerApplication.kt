package com.example.timer

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class TimerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        // DB migration を無視して、データ構造の更新があった場合には全てのデータを消しとばすようにした。
        val realmConfig = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}