package com.mkhaleghy.cinemakt.base

/**
 * Created by mk on 3/1/2018.
 */

interface Binder<in T : Element> {
    fun bind(item: T, mListener: OnAdapterInteractionListener)
}
