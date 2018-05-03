package com.mkhaleghy.cinemakt.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkhaleghy.cinemakt.R

object BaseInflater {
    val MOVIE_VIEW = 0
    val elementIds: Set<Pair<Int, Int>> = setOf(
            Pair(MOVIE_VIEW, R.layout.item_movie)
    )

    fun inflate(inflater: LayoutInflater, viewType: Int, parent: ViewGroup, attachToRoot: Boolean): View {
        return inflater.inflate(elementIds.find { it.first == viewType }!!.second, parent, attachToRoot)
    }
}
