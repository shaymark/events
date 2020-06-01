package com.markoapps.events

import android.app.Application
import android.content.Context

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
    }


}