package com.josty.genshin.dictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josty.genshin.databinding.ItemDictionaryBinding

class DictionaryAdapter(private var list: List<DictionaryListItem>) :
    RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDictionaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])
    override fun getItemCount() = if (list.isEmpty()) list.size else 0


    inner class ViewHolder(private val binding: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DictionaryListItem) {
            binding.title.text = item.title
            binding.content.text = item.content
            binding.description.text = item.description
        }
    }
}