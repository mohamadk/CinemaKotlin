package com.mkhaleghy.cinemakt.base

import android.arch.lifecycle.MutableLiveData
import com.mkhaleghy.cinemakt.main.DayList

/**
 * Created by mk on 3/8/2018.
 */

interface Repository {
    fun getData(elementsData: MutableLiveData<DayList>)
}
