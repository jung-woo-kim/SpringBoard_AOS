package com.springboard.zzatmari.config

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRVAdapter<Item, ItemBinding : ViewBinding>(
        val context: Context,
        private val inflate: (LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> ItemBinding
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected val items: ArrayList<Item> = ArrayList()
    protected lateinit var binding: ItemBinding

    inner class ItemViewHolder(bind: ItemBinding) :
            RecyclerView.ViewHolder(bind.root) {
        fun setItem(item: Item, position: Int) {
            viewBinding(item, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder: ItemViewHolder =
                holder as BaseRVAdapter<Item, ItemBinding>.ItemViewHolder
        itemViewHolder.setItem(items[position], position)
    }

    abstract fun viewBinding(item: Item, position: Int)

    fun addAllData(newItems: ArrayList<Item>) {
        val prevItemSize = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(prevItemSize, newItems.size)
    }

    fun removeAllData() {
        items.clear()
        notifyDataSetChanged()
    }

    open fun onItemClick(v: View?, item:Item, position:Int){

    }
}