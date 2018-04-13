package com.mkhaleghy.cinemakt.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View


open class BaseFragment : Fragment() {
    private var listener: OnBaseFragmentIntraction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBaseFragmentIntraction) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnBaseFragmentIntraction")
        }
    }

    fun onContentViewCreated(view: View) {

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnBaseFragmentIntraction {

    }
}
