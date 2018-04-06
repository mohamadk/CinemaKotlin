package com.mkhaleghy.cinemakt.main.daylist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkhaleghy.cinemakt.R
import com.mkhaleghy.cinemakt.base.OnAdapterInteractionListener
import com.mkhaleghy.cinemakt.base.RecyclerAdapter
import com.mkhaleghy.cinemakt.main.DayList
import com.mkhaleghy.cinemakt.tools.RecyclerOverScrollHandler
import kotlinx.android.synthetic.main.fragment_day_list.*
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator
import java.util.*


class DayListFragment : Fragment(), OnAdapterInteractionListener {
    val TAG = "DayListFragment"
    private lateinit var calendar: Calendar
    private lateinit var adapter: RecyclerAdapter

    private var mListener: OnFragmentInteractionListener? = null
    private lateinit var viewModel: DayListViewModel
    private var pos = -1

    internal var movieItemsObserver: Observer<DayList> = Observer { dayList ->
        Log.d(TAG, "onChanged() called with: elements.size = [" + dayList?.movies?.size + "]")
        adapter.bindItems(mutableListOf(dayList!!.movies))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pos = it.getInt("pos")
            calendar = Calendar.getInstance()
            calendar.time = Date(it.getLong(PARAM_DATE))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val mainView = inflater.inflate(R.layout.fragment_day_list, container, false)
        viewModel = ViewModelProviders.of(this).get(DayListViewModel::class.java)

        val layoutManager = LinearLayoutManager(activity)
        rv_list.layoutManager = layoutManager
        adapter = RecyclerAdapter(LayoutInflater.from(activity), this)
        rv_list.adapter = adapter

        val overScroll = RecyclerOverScrollHandler(mListener!!.appbarLayout(), rv_list)
        val decorator = VerticalOverScrollBounceEffectDecorator(overScroll)
        decorator.setOverScrollUpdateListener { decor, state, offset ->
            if (!overScroll.isInAbsoluteEnd) {
                mListener?.stretch(1 + offset / mainView.height, offset.toInt())
            }
        }

        viewModel.items.observe(this, movieItemsObserver)
        viewModel.start()

        return mainView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    override fun detailSelected() {
        mListener?.detailSelected()
    }

    interface OnFragmentInteractionListener {
        fun appbarLayout(): AppBarLayout

        fun stretch(offset: Float, offsetInPx: Int)

        fun detailSelected()
    }

    companion object {
        private const val PARAM_DATE = "date"

        fun newInstance(date: Date, pos: Int): DayListFragment {
            val fragment = DayListFragment()
            val args = Bundle()
            args.let {
                it.putLong(PARAM_DATE, date.time)
                it.putInt("pos", pos)
            }
            fragment.arguments = args
            return fragment
        }
    }


}
