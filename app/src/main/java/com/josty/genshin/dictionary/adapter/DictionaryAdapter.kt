package com.josty.genshin.dictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josty.genshin.databinding.ItemDictionaryBinding
import com.josty.genshin.dictionary.data.DictionaryEntity
import com.josty.genshin.list.adapter.ListAdapter

class DictionaryAdapter(list: List<DictionaryEntity>? = null) :
    ListAdapter<DictionaryEntity, DictionaryAdapter.ViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDictionaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list!![position])

    inner class ViewHolder(private val binding: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DictionaryEntity) {
            binding.title.text = item.title
            binding.content.text = item.content
            binding.description.text = item.description
        }
    }
}