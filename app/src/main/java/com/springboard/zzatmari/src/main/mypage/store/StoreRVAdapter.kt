package com.springboard.zzatmari.src.main.mypage.store

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.springboard.zzatmari.databinding.RowSeedStoreBinding
import com.springboard.zzatmari.src.main.mypage.store.models.Seed
import java.util.*

class StoreRVAdapter(val context: Context, val mylist:ArrayList<Seed>): RecyclerView.Adapter<StoreRVAdapter.CustomViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(v: View, data: Seed, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
    inner class CustomViewHolder(val binding: RowSeedStoreBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(item: Seed){
            binding.fragmentSeedStoreSeedName.text=item.seedName
            binding.fragmentSeedStoreSeedPrice.text=item.sunlight.toString()+"햇살"
            Glide.with(context).load(item.seedImgUrl).thumbnail(0.1f).into(binding.fragmentSeedStoreSeedImg)
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

        val binding= RowSeedStoreBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(mylist[position])
    }



}


