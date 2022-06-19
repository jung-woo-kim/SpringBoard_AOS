package com.springboard.zzatmari.src.main.stat

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springboard.zzatmari.config.BaseRVAdapter
import com.springboard.zzatmari.databinding.RowStatKindsBinding
import com.springboard.zzatmari.src.main.stat.models.CalendarListItem

class StatKindsRVAdapter(context: Context):BaseRVAdapter<CalendarListItem, RowStatKindsBinding>(context = context, inflate = RowStatKindsBinding::inflate) {

    @SuppressLint("SetTextI18n")
    override fun viewBinding(item: CalendarListItem, position: Int) {
        binding.textView.text=item.listItem
        binding.statKindsMinText.text=item.time.toString()+" min"

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, viewType)
    }


}