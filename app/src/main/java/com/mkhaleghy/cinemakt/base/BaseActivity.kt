package com.mkhaleghy.cinemakt.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.WindowManager
import com.balysv.materialmenu.MaterialMenuDrawable
import com.mkhaleghy.cinemakt.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by mk on 3/16/2018.
 */

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var materialMenu: MaterialMenuDrawable
    protected abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        setContentView(layout)
        materialMenu = MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.REGULAR)
        toolbar.navigationIcon = materialMenu

        setSupportActionBar(toolbar)

    }
}
