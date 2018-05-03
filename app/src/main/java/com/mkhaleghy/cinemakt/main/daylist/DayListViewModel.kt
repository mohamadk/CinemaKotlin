package com.mkhaleghy.cinemakt.main.daylist


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mkhaleghy.cinemakt.base.Repository
import com.mkhaleghy.cinemakt.main.DayList


/**
 * Created by mk on 3/1/2018.
 */

class DayListViewModel : ViewModel() {
    internal var items = MutableLiveData<DayList>()
    private lateinit var dataFetcher: Repository


    fun start() {
        dataFetcher = FakeRepository
        dataFetcher.getData(items)
    }
}
