package com.josty.genshin.list.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView

abstract class ListAdapter<T, VH : RecyclerView.ViewHolder>(protected var list: List<T>? = null) :
    RecyclerView.Adapter<VH>() {

    @JvmName("setMyList")
    @SuppressLint("NotifyDataSetChanged")
    fun setList(l: List<T>) {
        list = l
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if (!list.isNullOrEmpty()) list!!.size else 0
}