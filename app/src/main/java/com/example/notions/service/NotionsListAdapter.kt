package com.example.notions.service

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notions.databinding.ItemNotionBinding
import com.example.notions.entity.Notion

class NotionsListAdapter: RecyclerView.Adapter<NotionsListAdapter.NotionListViewHolder>(){

    class NotionListViewHolder(val binding: ItemNotionBinding)
        : RecyclerView.ViewHolder(binding.root)

    var notions = emptyList<Notion>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotionListViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding=ItemNotionBinding.inflate(inflater,parent,false)
        return NotionListViewHolder(binding)
    }

    override fun getItemCount(): Int = notions.size

    override fun onBindViewHolder(holder: NotionListViewHolder, position: Int) {
        holder.itemView.tag=notions[position]
        holder.binding.notionHead.text=notions[position].title
        holder.binding.notionBody.text=notions[position].text
    }
}