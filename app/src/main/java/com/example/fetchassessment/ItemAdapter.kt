package com.example.fetchassessment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val items: Map<Int, List<Item>>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listIdTextView: TextView = itemView.findViewById(R.id.listIdTextView)
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val listId = items.keys.elementAt(position)
        val itemList = items[listId]
        holder.listIdTextView.text = "List ID: $listId"
        holder.itemNameTextView.text = itemList?.joinToString(separator = "\n") { it.name.toString() } ?: ""
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
