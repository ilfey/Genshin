package com.josty.genshin.wishes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josty.genshin.R
import com.josty.genshin.databinding.ItemWishesBinding
import com.josty.genshin.list.adapter.ListAdapter
import com.josty.genshin.wishes.data.WishesEntity
import com.squareup.picasso.Picasso

class WishesAdapter(list: List<WishesEntity>? = null) :
    ListAdapter<WishesEntity, WishesAdapter.ViewHolder>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishesAdapter.ViewHolder {
        val binding = ItemWishesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list!![position])

    inner class ViewHolder(private val binding: ItemWishesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WishesEntity) {
            binding.title.text = item.title
//            TODO fix backend
            /*binding.star.text = item.star
            binding.star1.text = item.star1
            binding.star2.text = item.star2
            binding.star3.text = item.star3*/
            Picasso.get()
                .load(item.icon)
                .error(R.drawable.ic_error)
                .into(binding.icon)
        }
    }
}