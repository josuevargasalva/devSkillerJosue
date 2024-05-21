package com.example.devskillerjosue.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//Base adapter, used for reusable code in other adapters
abstract class BaseAdapter<T, VH: BaseAdapter.BaseViewHolder<T>> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var adapterContext: Context
    abstract var itemLayout: Int
    var onItemClick: ((T) -> Unit)? = null
    var list: List<T> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        adapterContext = parent.context
        return instantiateViewHolder(v)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BaseViewHolder<*>) {
            holder as VH
            holder.showItem(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    abstract fun instantiateViewHolder(view: View): RecyclerView.ViewHolder

    abstract class BaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        abstract fun showItem(item: T)

    }
}