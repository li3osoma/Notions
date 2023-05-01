package com.example.notions.notions.service

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notions.databinding.ItemNotionBinding
import com.example.notions.notions.entity.Notion
interface NotionClickListener{
    fun openChosen(notionId:Int)
}
class NotionsListAdapter(private val notionClickListener: NotionClickListener): RecyclerView.Adapter<NotionsListAdapter.NotionListViewHolder>(){
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NotionListViewHolder, position: Int) {
        holder.itemView.tag=notions[position]
        holder.binding.notionHead.text=cutString(notions[position].title)
        holder.binding.notionBody.text=cutString(notions[position].text)

        holder.binding.notionDate.text=notions[position].date

        holder.itemView.setOnClickListener { notionClickListener.openChosen(notions[position].id) }
    }

    private fun cutString(s:String):String{
        val s1=s.replace("\n", System.getProperty("line.separator")!!.toString());
        return if(s1.substringBefore("\n").length<=20) {
            if(s1 == s1.substringBefore("\n")) s1.substringBefore("\n")
            else "${s1.substringBefore("\n")}..."
        } else "${s1.substring(0,21)}..."
    }
}