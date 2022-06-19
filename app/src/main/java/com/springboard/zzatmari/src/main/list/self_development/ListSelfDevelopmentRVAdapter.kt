package com.springboard.zzatmari.src.main.list.self_development

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springboard.zzatmari.databinding.RowSelfDevelopmentBinding
import com.springboard.zzatmari.src.main.list.models.GetListItemResult
import com.springboard.zzatmari.src.main.list.timer.list.TimerListActivity

class ListSelfDevelopmentRVAdapter(val context: Context,val mylist:ArrayList<GetListItemResult>): RecyclerView.Adapter<ListSelfDevelopmentRVAdapter.CustomViewHolder>() {
    interface OnItemLongClickListener{
        fun onItemLongClick(v: View, data: GetListItemResult, pos : Int)
    }
    private var listener : OnItemLongClickListener? = null
    fun setOnItemClickListener(listener : OnItemLongClickListener) {
        this.listener = listener
    }
    inner class CustomViewHolder(val binding: RowSelfDevelopmentBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(item:GetListItemResult){
            binding.rowSelfDevelopTextView.text=item.listItem
            if (item.goalTime!=0){
                binding.selfDevelopMinText.text=item.time.toString()+" / "+item.goalTime+" min"
            }else{
                binding.selfDevelopMinText.text=item.time.toString()+" min"
            }
            binding.rowSelfDevelopTextView.setTypeface(binding.rowSelfDevelopTextView.typeface, Typeface.NORMAL)
            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                binding.root.setOnLongClickListener {
                    listener?.onItemLongClick(itemView,item,pos)

                    return@setOnLongClickListener true
                }
                binding.root.setOnClickListener {
                    val intent=Intent(context, TimerListActivity::class.java)
                    intent.putExtra("listIdx",item.listIdx)
                    context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding=RowSelfDevelopmentBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(mylist[position])
    }

}