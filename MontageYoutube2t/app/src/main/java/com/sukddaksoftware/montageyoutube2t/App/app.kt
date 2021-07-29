package com.sukddaksoftware.montageyoutube2t.App

import android.app.Application
import android.content.Context

class app : Application() {
    init{
        instance = this
    }

    companion object {
        lateinit var instance: app
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }
}