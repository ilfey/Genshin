package com.josty.genshin.characters.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josty.genshin.R
import com.josty.genshin.databinding.ItemCharactersBinding
import com.squareup.picasso.Picasso


class CharactersAdapter(private var list: List<CharactersListItem>? = null) :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setList(l: List<CharactersListItem>) {
        list = l
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list!![position])
    override fun getItemCount(): Int = if (!list.isNullOrEmpty()) list!!.size else 0


    inner class ViewHolder(private val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharactersListItem) {
            binding.title.text = item.title
            binding.star.text = item.star
            Picasso.get()
                .load(item.icon)
                .error(R.drawable.ic_error)
                .into(binding.icon)
        }
    }
}