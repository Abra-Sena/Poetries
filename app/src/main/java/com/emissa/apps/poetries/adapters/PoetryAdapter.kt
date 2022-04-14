package com.emissa.apps.poetries.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


class PoetryViewHolder<T> internal constructor(
    private val binding: ViewBinding,
    private val viewInitializer: (T, ViewBinding) -> Unit,
    private val itemClicked: ItemClickListener<T>
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.root.setOnClickListener {
            itemClicked.onItemClicked(item)
        }
    }
 }

interface ItemClickListener<T> {
    fun onItemClicked(item: T)
}

class PoetryAdapter<T>(
    private val itemClickListener: ItemClickListener<T>,
    private var listOfItem: MutableList<T> = mutableListOf()
) : RecyclerView.Adapter<PoetryViewHolder<T>>() {

    fun setItems(itemList: List<T>) {
        listOfItem.clear()
        listOfItem.addAll(itemList)
        notifyItemRangeChanged(0, itemCount)
    }

    var theViewHolderBinding: ((T, ViewBinding) -> Unit)? = null
    var theOnCreateViewHolder: ((ViewGroup) -> ViewBinding)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoetryViewHolder<T> {
        return theOnCreateViewHolder?.let {
            it(parent)
        }?.let {
            PoetryViewHolder(it, theViewHolderBinding!!, itemClickListener)
        }!!
    }
    override fun onBindViewHolder(holder: PoetryViewHolder<T>, position: Int) {
        val item = listOfItem!![position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listOfItem!!.size
}