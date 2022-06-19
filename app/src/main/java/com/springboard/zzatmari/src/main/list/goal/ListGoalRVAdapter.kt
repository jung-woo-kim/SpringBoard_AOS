package com.springboard.zzatmari.src.main.list.goal

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springboard.zzatmari.databinding.RowGoalBinding
import com.springboard.zzatmari.src.main.list.models.GetListItemResult

class ListGoalRVAdapter(val context: Context,val mylist:ArrayList<GetListItemResult>): RecyclerView.Adapter<ListGoalRVAdapter.CustomViewHolder>() {
    private var totalMinute=0
    interface OnItemClickListener{
        fun onItemClick(v:View, data: GetListItemResult, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
    inner class CustomViewHolder(val binding: RowGoalBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(item: GetListItemResult){
            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION){
                binding.root.setOnClickListener {
                    listener?.onItemClick(itemView,item,pos)
                }
            }

            binding.textView.text=item.listItem
            binding.goalMinText.text=item.time.toString()+" min"
        }

    }
    fun getTotalMin(): Int {
        for(i in mylist){
            totalMinute+=i.time
        }
        return totalMinute
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding= RowGoalBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(mylist[position])
    }

}