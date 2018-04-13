package com.mkhaleghy.cinemakt.main.daylist

import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mkhaleghy.cinemakt.app.Ci

import com.mkhaleghy.cinemakt.base.Repository
import com.mkhaleghy.cinemakt.main.DayList
import com.mkhaleghy.cinemakt.tools.Utils

/**
 * Created by mk on 3/8/2018.
 */

object FakeRepository : Repository {
    override fun getData(elementsData: MutableLiveData<DayList>) {
        val dayList = Gson().fromJson(Utils.loadFileFromAssets("daylist.json"),DayList::class.java)
        elementsData.postValue(dayList)
    }
}
