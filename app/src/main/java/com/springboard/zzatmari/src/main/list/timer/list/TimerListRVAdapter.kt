package com.springboard.zzatmari.src.main.list.timer.list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springboard.zzatmari.databinding.RowTimerBinding
import com.springboard.zzatmari.src.main.list.timer.TimerActivity
import com.springboard.zzatmari.src.main.list.timer.list.models.GetTimerListResult
import java.util.*

class TimerListRVAdapter(val context: Context, var mylist:ArrayList<GetTimerListResult>,val listIdx:Int): RecyclerView.Adapter<TimerListRVAdapter.CustomViewHolder>() {
    interface OnItemLongClickListener{
        fun onItemLongClick(v: View, data: GetTimerListResult, pos : Int)
    }
    private var listener : OnItemLongClickListener? = null
    fun setOnItemClickListener(listener : OnItemLongClickListener) {
        this.listener = listener
    }
    inner class CustomViewHolder(val binding: RowTimerBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(item: GetTimerListResult){
            if (item.hour!=0){
                binding.toDoHourText.text=item.hour.toString()+"시간 "
            }
            if (item.minute!=0){
                binding.toDoMinuteText.text=item.minute.toString()+"분"
            }

            binding.endTimeText.text=item.time


            binding.root.isLongClickable=true
            binding.toDoHourText.setTypeface(binding.toDoHourText.typeface, Typeface.NORMAL)
            binding.toDoMinuteText.setTypeface(binding.toDoMinuteText.typeface, Typeface.NORMAL)
            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                binding.root.setOnLongClickListener {
                    listener?.onItemLongClick(itemView,item,pos)

                    return@setOnLongClickListener true
                }
                binding.root.setOnClickListener {
                    val intent=Intent(context, TimerActivity::class.java)
                    intent.putExtra("listIdx",listIdx)
                    intent.putExtra("timerIdx",item.timerIdx)
                    intent.putExtra("hour",item.hour)
                    intent.putExtra("minute",item.minute)
                    context.startActivity(intent)
                }
            }

        }

    }
    fun setData(list:ArrayList<GetTimerListResult>){
        this.mylist.clear()
        this.mylist.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val binding= RowTimerBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(mylist[position])
    }



}


