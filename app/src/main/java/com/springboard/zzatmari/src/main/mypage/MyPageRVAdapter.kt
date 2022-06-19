package com.springboard.zzatmari.src.main.mypage

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.springboard.zzatmari.databinding.RowMypagePlantBinding
import java.util.*

class MyPageRVAdapter(val context: Context, val mylist:ArrayList<PlantData>): RecyclerView.Adapter<MyPageRVAdapter.CustomViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(v: View, data: PlantData, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
    inner class CustomViewHolder(val binding: RowMypagePlantBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(item: PlantData){
            if (item.plantImgUrl1!=""){
                Glide.with(context).load(item.plantImgUrl1).thumbnail(0.1f).into(binding.rowMypagePlant1)
            }
            if (item.plantImgUrl2!=""){
                Glide.with(context).load(item.plantImgUrl2).thumbnail(0.1f).into(binding.rowMypagePlant2)
            }
            if (item.plantImgUrl3!=""){
                Glide.with(context).load(item.plantImgUrl2).thumbnail(0.1f).into(binding.rowMypagePlant3)
            }



            val pos = adapterPosition
//            if(pos!= RecyclerView.NO_POSITION)
//            {
//
//                binding.root.setOnClickListener {
//                    listener?.onItemClick(itemView,item,pos)
//                }
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val binding= RowMypagePlantBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(mylist[position])
    }



}


