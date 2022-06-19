package com.springboard.zzatmari.util

import androidx.recyclerview.widget.DiffUtil
import com.springboard.zzatmari.src.main.list.timer.list.models.GetTimerListResult

class ContactDiffUtil(private val oldList: ArrayList<GetTimerListResult>, private val currentList: ArrayList<GetTimerListResult>): DiffUtil.Callback(){
    override fun getOldListSize(): Int =oldList.size

    override fun getNewListSize(): Int =currentList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==currentList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]==currentList[newItemPosition]
    }

}