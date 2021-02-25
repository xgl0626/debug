package com.example.d2doctor.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import cn.jpush.im.android.api.JMessageClient

class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        JMessageClient.init(context)
    }
}