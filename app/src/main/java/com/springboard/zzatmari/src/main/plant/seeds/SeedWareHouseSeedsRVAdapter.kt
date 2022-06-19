package com.springboard.zzatmari.src.main.plant.seeds

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.springboard.zzatmari.databinding.RowSeedWarehouseBinding
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseResult
import java.util.*

class SeedWareHouseSeedsRVAdapter(val context: Context, val mylist:ArrayList<GetSeedWareHouseResult>): RecyclerView.Adapter<SeedWareHouseSeedsRVAdapter.CustomViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(v: View, data: GetSeedWareHouseResult, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
    inner class CustomViewHolder(val binding: RowSeedWarehouseBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(item: GetSeedWareHouseResult){
            binding.rowSeedWarehouseText.text=item.seedName

            Glide.with(context).load(item.seedImgUrl).thumbnail(0.1f).into(binding.rowSeedWarehouseImg)
            val pos = adapterPosition
            if(pos!= RecyclerView.NO_POSITION)
            {

                binding.root.setOnClickListener {
                    listener?.onItemClick(itemView,item,pos)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val binding= RowSeedWarehouseBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(mylist[position])
    }



}


