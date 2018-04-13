package com.mkhaleghy.cinemakt.main.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mkhaleghy.cinemakt.main.InfoPages

/**
 * Created by mk on 3/8/2018.
 */

class InfoPageAdapter(fm: FragmentManager, infos: List<InfoPages> = mutableListOf()) : FragmentPagerAdapter(fm) {
    internal var infos: MutableList<InfoPages> = mutableListOf()
    internal var fragments: MutableList<InfoFragment> = mutableListOf()

    override fun getItem(position: Int): Fragment {
        if (position >= fragments.size) {
            fragments.add(InfoFragment.newInstance(infos[position]))
        }
        return fragments[position]
    }

    override fun getCount(): Int {
        return infos.size
    }

    init {
        this.infos = infos.toMutableList()
    }
}
