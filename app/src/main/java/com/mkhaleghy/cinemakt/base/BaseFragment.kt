package com.mkhaleghy.cinemakt.base

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.balysv.materialmenu.MaterialMenuDrawable
import com.mkhaleghy.cinemakt.R
import kotlinx.android.synthetic.main.fragment_calendar.*


open class BaseFragment : Fragment() {
    private var listener: OnBaseFragmentIntraction? = null
    protected lateinit var materialMenu: MaterialMenuDrawable

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

    fun onContentViewCreated(view:View){
        materialMenu = MaterialMenuDrawable(activity, Color.WHITE, MaterialMenuDrawable.Stroke.REGULAR)
        toolbar.navigationIcon = materialMenu
        toolbar.setBackgroundColor(Color.RED)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    interface OnBaseFragmentIntraction {

    }
}
