package com.mkhaleghy.cinemakt.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.SparseArray
import com.mkhaleghy.cinemakt.R
import com.mkhaleghy.cinemakt.app.Ci

import com.mkhaleghy.cinemakt.main.daylist.DayListFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by mk on 3/2/2018.
 */

class MainPagerAdapter(fm: FragmentManager, private val pageCount: Int = 30, private val listener: DayListFragment.OnFragmentInteractionListener) : FragmentPagerAdapter(fm) {
    internal var sdf = DateFormat.getDateInstance()

    private val items = SparseArray<DayListFragment>()


    override fun getItem(position: Int): Fragment {
        val fragment: DayListFragment
        if (position >= items.size()) {
            fragment = DayListFragment.newInstance(getItemTime(position), position)
            fragment.mListener=listener
            items.append(position, fragment)
        } else {
            fragment = items.get(position)
        }

        return fragment
    }

    private fun getItemTime(position: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, position)
        return calendar.time
    }

    fun item(position: Int): DayListFragment {
        return items.get(position)
    }

    override fun getCount(): Int {
        return pageCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return getTitle(position)
    }

    private fun getTitle(position: Int): CharSequence {
        return when (position) {
            0 -> Ci.context.getString(R.string.today)
            else -> sdf.format(getItemTime(position))
        }
    }
}
