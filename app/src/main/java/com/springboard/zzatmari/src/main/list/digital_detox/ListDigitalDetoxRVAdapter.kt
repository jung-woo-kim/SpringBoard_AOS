package com.springboard.zzatmari.src.main.list.digital_detox

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.springboard.zzatmari.databinding.RowDigitalDetoxBinding
import com.springboard.zzatmari.src.main.list.models.GetListItemResult
import com.springboard.zzatmari.src.main.list.timer.list.TimerListActivity

class ListDigitalDetoxRVAdapter(val context: Context,val mylist:ArrayList<GetListItemResult>): RecyclerView.Adapter<ListDigitalDetoxRVAdapter.CustomViewHolder>() {
    interface OnItemLongClickListener{
        fun onItemLongClick(v:View, data: GetListItemResult, pos : Int)
    }
//    interface OnItemClickListener{
//        fun onItemClick(v:View, data: GetListItemResult, pos : Int)
//    }
//    private var Listener : OnItemClickListener? = null
//    fun setOnItemClickListener(listener : OnItemClickListener) {
//        this.Listener = listener
//    }
    private var longListener : OnItemLongClickListener? = null
    fun setOnItemLongClickListener(listener : OnItemLongClickListener) {
        this.longListener = listener
    }
    inner class CustomViewHolder(val binding: RowDigitalDetoxBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(title:GetListItemResult){
            binding.rowDigitalDetoxTextView.text=title.listItem
            if (title.goalTime!=0){
                binding.digitalDetoxMinText.text=title.time.toString()+" / "+title.goalTime+" min"
            }else{
                binding.digitalDetoxMinText.text=title.time.toString()+" min"
            }

            binding.root.isLongClickable=true
            binding.digitalDetoxMinText.setTypeface(binding.digitalDetoxMinText.typeface,Typeface.NORMAL)

            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {
                binding.root.setOnLongClickListener {
                    longListener?.onItemLongClick(itemView,title,pos)

                    return@setOnLongClickListener true
                }
                binding.root.setOnClickListener {

                    val intent=Intent(context, TimerListActivity::class.java)
                    intent.putExtra("listIdx",title.listIdx)
                    context.startActivity(intent)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val binding=RowDigitalDetoxBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(mylist[position])
    }

}


