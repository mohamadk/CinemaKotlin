package com.mkhaleghy.cinemakt.main

import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.mkhaleghy.cinemakt.R

/**
 * Created by mk on 3/3/2018.
 */

class ViewPagerTransform(var offset: Float = .5F) : ViewPager.PageTransformer {
    val TAG = "ViewPagerTransform"

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    internal lateinit var visibleItems: Array<View>
    internal var mainView: View? = null

    override fun transformPage(view: View, position: Float) {
        when (position) {
            in -1..1 -> {
                if (mainView != view) {
                    mainView = view
                    recyclerView = view.findViewById(R.id.rv_list)
                    layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    findVisibleItems()
                }

                val offsetStep = offset / visibleItems.size
                for (i in visibleItems.indices) {
                    val item = visibleItems[i] ?: continue
                    val x = if (position < 0) visibleItems.size - i else i
                    item.translationX = x.toFloat() * offsetStep * item.width.toFloat() * position
                }
            }
        }
    }

    private fun findVisibleItems() {
        var firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//        if (firstVisibleItemPosition > 0) {
//            firstVisibleItemPosition--
//        }

        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

        visibleItems = Array(lastVisibleItemPosition - firstVisibleItemPosition + 1,
                { layoutManager.getChildAt(it) }
        )
    }


}
