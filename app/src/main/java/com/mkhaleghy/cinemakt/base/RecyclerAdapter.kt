package com.mkhaleghy.cinemakt.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mkhaleghy.cinemakt.R

/**
 * Created by mk on 3/1/2018.
 */

class RecyclerAdapter(private val inflater: LayoutInflater,private val listener:OnAdapterInteractionListener) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder1>() {

    //in case of add and remove or if you don't have that case just change mutableList to list
    // and get ride of casts
    private lateinit var items: MutableList<*>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {
        return ViewHolder1(inflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return (items[position] as Element).type
    }

    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {
        holder.bind((items[position] as Element), listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun bindItems(elements: MutableList<*>) {
        items = elements
        notifyDataSetChanged()
    }


    class ViewHolder1(internal var itemView1: View) : RecyclerView.ViewHolder(itemView1) {
        fun bind(item: Element, mListener: com.mkhaleghy.cinemakt.base.OnAdapterInteractionListener) {
            (itemView1 as Binder<Element>).bind(item, mListener)
        }
    }
}