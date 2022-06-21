package com.josty.genshin.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josty.genshin.R
import com.josty.genshin.characters.data.CharactersEntity
import com.josty.genshin.databinding.ItemCharactersBinding
import com.josty.genshin.list.adapter.ListAdapter
import com.squareup.picasso.Picasso


class CharactersAdapter(list: List<CharactersEntity>? = null) :
    ListAdapter<CharactersEntity, CharactersAdapter.ViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list!![position])

    inner class ViewHolder(private val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharactersEntity) {
            binding.title.text = item.name
            binding.star.text = item.rarity.toString()
            Picasso.get()
                .load(item.card)
                .error(R.drawable.ic_error)
                .into(binding.icon)
        }
    }
}