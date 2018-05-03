package com.mkhaleghy.cinemakt.tools

import android.support.design.widget.AppBarLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter

/**
 * Created by mk on 3/16/2018.
 */

class RecyclerOverScrollHandler : RecyclerViewOverScrollDecorAdapter, AppBarLayout.OnOffsetChangedListener {
    val TAG = "HandleAppbarLayout"
    var isFullyExpanded = false

    constructor(recyclerView: RecyclerView) : super(recyclerView) {}

    constructor(appBarLayout: AppBarLayout, recyclerView: RecyclerView) : super(recyclerView) {
        appBarLayout.addOnOffsetChangedListener(this)
    }

    protected constructor(
            recyclerView: RecyclerView,
            impl: RecyclerViewOverScrollDecorAdapter.Impl) : super(recyclerView, impl) {}

    constructor(recyclerView: RecyclerView
                , itemTouchHelperCallback: ItemTouchHelper.Callback) : super(recyclerView, itemTouchHelperCallback) {}

    protected constructor(recyclerView: RecyclerView
                , impl: RecyclerViewOverScrollDecorAdapter.Impl
                , itemTouchHelperCallback: ItemTouchHelper.Callback) : super(recyclerView, impl, itemTouchHelperCallback) {}


    override fun isInAbsoluteStart(): Boolean {
        return super.isInAbsoluteStart() && isFullyExpanded
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        isFullyExpanded = verticalOffset == 0
    }

}
