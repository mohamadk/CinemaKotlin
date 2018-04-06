package com.mkhaleghy.cinemakt

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mkhaleghy.cinemakt.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity: AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
        text.text = "some text"


        val file=getExternalFilesDir("getExternalFilesDir.txt")
        file.mkdirs()

        val fileEnvironment=File(Environment.getExternalStorageDirectory().absolutePath+File.separator+"fileEnviroment.txt")
        val res=fileEnvironment.mkdirs()

        Log.d(TAG, "oncreate: fileEnvironment=$fileEnvironment res=$res");


    }


}
