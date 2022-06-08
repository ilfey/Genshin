package com.josty.genshin.wishes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josty.genshin.databinding.ItemWishesBinding

class WishesAdapter(private var list: List<WishesListItem>) :
    RecyclerView.Adapter<WishesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishesAdapter.ViewHolder {
        val binding = ItemWishesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishesAdapter.ViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = if (list.isNotEmpty()) list.size else 0


    inner class ViewHolder(val binding: ItemWishesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WishesListItem) {
//            binding.icon
//            TODO fill ImageView
            binding.star.starText.text = item.star
            binding.star1.starText.text = item.star1
            binding.star2.starText.text = item.star2
            binding.star3.starText.text = item.star3
        }
    }
}