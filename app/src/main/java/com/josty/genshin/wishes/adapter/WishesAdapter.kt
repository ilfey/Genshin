package com.josty.genshin.wishes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josty.genshin.R
import com.josty.genshin.databinding.ItemWishesBinding
import com.squareup.picasso.Picasso

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
            binding.star.text = item.star
            binding.star1.text = item.star1
            binding.star2.text = item.star2
            binding.star3.text = item.star3
            Picasso.get()
                .load(item.icon)
                .error(R.drawable.ic_error)
                .into(binding.icon)
        }
    }
}