package com.mkhaleghy.cinemakt

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import com.mkhaleghy.cinemakt.base.BaseActivity
import com.mkhaleghy.cinemakt.base.BaseFragment
import com.mkhaleghy.cinemakt.main.CalendarFragment
import com.mkhaleghy.cinemakt.main.daylist.DayListFragment
import kotlinx.android.synthetic.main.fragment_calendar.*

class MainActivity(override val layout: Int = R.layout.activity_main) : BaseActivity()
        , BaseFragment.OnBaseFragmentIntraction
        , CalendarFragment.OnFragmentInteractionListener

{

    val calendarFragment=CalendarFragment.newInstance()
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportFragmentManager.beginTransaction().replace(R.id.mainLay,calendarFragment).commit()
    }


}
