package com.mkhaleghy.cinemakt

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import com.balysv.materialmenu.MaterialMenuDrawable
import com.mkhaleghy.cinemakt.base.BaseActivity
import com.mkhaleghy.cinemakt.base.BaseFragment
import com.mkhaleghy.cinemakt.main.MainPagerAdapter
import com.mkhaleghy.cinemakt.main.ViewPagerTransform
import com.mkhaleghy.cinemakt.main.daylist.DayListFragment
import com.mkhaleghy.cinemakt.tools.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity(override val layout: Int = R.layout.activity_main) : BaseActivity()
        ,DayListFragment.OnDayListInteractionListener
        , BaseFragment.OnBaseFragmentIntraction
{
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apl_appbar.setPadding(apl_appbar.paddingLeft, Utils.statusBarHeight, apl_appbar.paddingRight, apl_appbar.paddingBottom)

        val pagerAdapter = MainPagerAdapter(supportFragmentManager, 30,this)

        vp_pager.adapter = pagerAdapter
        vp_pager.setPageTransformer(true, ViewPagerTransform())

        stl_tabs.setSelectedIndicatorColors(Color.TRANSPARENT)
        stl_tabs.setSelectedScaleFactor(.8f)
        stl_tabs.setViewPager(vp_pager)
    }

    override fun stretch(offset: Float, offsetInPx: Int) {
        apl_appbar.translationY = (offset - 1) * (apl_appbar.height / 2)
        apl_appbar.scaleY = offset
        riv_ramp.rampDy= (-offsetInPx).toFloat()// -offsetInPx "-" because riv_ramp rotated 180
    }

    override fun appbarLayout(): AppBarLayout {
        return apl_appbar
    }

    override fun detailSelected() {
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW)
    }
}
