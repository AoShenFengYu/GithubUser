package com.aoshenfengyu.androidexercise

import android.app.Application
import com.aoshenfengyu.androidexercise.request.RequestManager.Companion.init

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        init(this)
    }
}