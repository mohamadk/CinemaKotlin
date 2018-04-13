package com.mkhaleghy.cinemakt.main

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.balysv.materialmenu.MaterialMenuDrawable
import com.mkhaleghy.cinemakt.R
import com.mkhaleghy.cinemakt.base.BaseFragment
import com.mkhaleghy.cinemakt.main.daylist.DayListFragment
import com.mkhaleghy.cinemakt.tools.Utils
import kotlinx.android.synthetic.main.fragment_calendar.*


class CalendarFragment : BaseFragment() ,
        DayListFragment.OnFragmentInteractionListener {
    private var listener: OnFragmentInteractionListener? = null

    override fun appbarLayout(): AppBarLayout {
        return apl_appbar
    }

    override fun stretch(offset: Float, offsetInPx: Int) {
        apl_appbar.translationY = (offset - 1) * (apl_appbar.height / 2)
        apl_appbar.scaleY = offset
        riv_ramp.rampDy= (-offsetInPx).toFloat()// -offsetInPx "-" because riv_ramp rotated 180
    }

    override fun detailSelected() {
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerAdapter = MainPagerAdapter(childFragmentManager, 30,this)
        apl_appbar.setPadding(apl_appbar.paddingLeft, Utils.statusBarHeight, apl_appbar.paddingRight, apl_appbar.paddingBottom)

        vp_pager.adapter = pagerAdapter
        vp_pager.setPageTransformer(true, ViewPagerTransform())

        stl_tabs.setSelectedIndicatorColors(Color.TRANSPARENT)
        stl_tabs.setSelectedScaleFactor(.8f)
        stl_tabs.setViewPager(vp_pager)
//        main_collapsing.statusBarScrim = ColorDrawable(Color.YELLOW)
//        main_collapsing.setScrimsShown(true)
        onContentViewCreated(view)

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
