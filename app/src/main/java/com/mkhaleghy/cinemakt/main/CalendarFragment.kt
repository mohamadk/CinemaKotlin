package com.mkhaleghy.cinemakt.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkhaleghy.cinemakt.R
import com.mkhaleghy.cinemakt.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_calendar.*


class CalendarFragment : BaseFragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val pagerAdapter = MainPagerAdapter(childFragmentManager, 30)
        vp_pager.adapter = pagerAdapter
        vp_pager.setPageTransformer(true, ViewPagerTransform())

        stl_tabs.setSelectedIndicatorColors(Color.TRANSPARENT)
        stl_tabs.setSelectedScaleFactor(.8f)
        stl_tabs.setViewPager(vp_pager)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {

    }

    companion object {
        @JvmStatic
        fun newInstance() =
                CalendarFragment()
    }
}
