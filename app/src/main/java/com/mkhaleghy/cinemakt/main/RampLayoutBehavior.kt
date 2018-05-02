package com.mkhaleghy.cinemakt.main

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.mkhaleghy.cinemakt.R
import com.mkhaleghy.cinemakt.tools.views.RampImageView

/**
 * Created by mk on 3/15/2018.
 */

class RampLayoutBehavior constructor(context: Context, attrs: AttributeSet) :
        CoordinatorLayout.Behavior<RampImageView>(context, attrs) {
    val TAG = "RampLayoutBehavior"

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: RampImageView?, dependency: View?): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: RampImageView, dependency: View): Boolean {
        val scaleFactor = (if (dependency.y<0) 1.5f else 1f)
        child.translationY =
                (dependency.y
                        + (dependency.height * (dependency.scaleY - scaleFactor)) / 2f
                        + dependency.bottom).toInt()
                        .toFloat()

        val collapsingToolbar = dependency.findViewById<CollapsingToolbarLayout>(R.id.main_collapsing)
        collapsingToolbar.alpha = 1 - Math.abs(dependency.top.toFloat() / (dependency as AppBarLayout).totalScrollRange.toFloat())

        return true
    }
}
