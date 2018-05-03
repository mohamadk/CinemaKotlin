package com.mkhaleghy.cinemakt.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by mk on 3/1/2018.
 */

class RecyclerAdapter(private val inflater: LayoutInflater,private val listener:OnAdapterInteractionListener) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    //in case of add and remove or if you don't have that case just change mutableList to list
    // and get ride of casts
    private var items = mutableListOf<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(BaseInflater.inflate(
                inflater,
                viewType,
                parent,
                false
        ))
    }

    override fun getItemViewType(position: Int): Int {
        return (items[position] as Element).type
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind((items[position] as Element), listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun bindItems(elements: MutableList<Any>) {
        items = elements
        notifyDataSetChanged()
    }


    class ViewHolder(internal var itemView1: View) : RecyclerView.ViewHolder(itemView1) {
        fun bind(item: Element, mListener: com.mkhaleghy.cinemakt.base.OnAdapterInteractionListener) {
            (itemView1 as Binder<Element>).bind(item, mListener)
        }
    }
}