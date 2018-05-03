package com.mkhaleghy.cinemakt.app

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mkhaleghy.cinemakt.base.BaseInflater

class Ci : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

        gson = GsonBuilder()
                .create()

    }

    companion object Cinema {
        lateinit var context: Application
        lateinit var gson: Gson
    }

}