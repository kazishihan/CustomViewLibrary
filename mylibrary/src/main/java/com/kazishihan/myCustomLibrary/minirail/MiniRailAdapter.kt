package com.kazishihan.myCustomLibrary.minirail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kazishihan.myCustomLibrary.R

class MiniRailAdapter : ListAdapter<String, MiniRailAdapter.MiniRailViewHolder>(DiffCallback()) {
    
    class MiniRailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemTextView)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiniRailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mini_rail, parent, false)
        return MiniRailViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: MiniRailViewHolder, position: Int) {
        holder.textView.text = getItem(position)
    }
    
    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }
}