package com.josty.genshin.wishes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josty.genshin.R
import com.josty.genshin.characters.adapter.CharactersAdapter
import com.josty.genshin.databinding.FragmentWishesBinding
import com.josty.genshin.wishes.adapter.WishesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishesFragment : Fragment() {
    private val viewModel by viewModel<WishesViewModel>()
    private lateinit var binding: FragmentWishesBinding
    private lateinit var adapter: WishesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishesBinding.inflate(layoutInflater)

        adapter = WishesAdapter()
        binding.recycler.adapter = adapter
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
        return binding.root
    }
}