package com.josty.genshin.wishes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josty.genshin.list.ui.ListFragment
import com.josty.genshin.wishes.adapter.WishesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WishesFragment : ListFragment() {

    override val viewModel by viewModel<WishesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val listAdapter = WishesAdapter()
        binding.recycler.adapter = listAdapter

        viewModel.list.observe(viewLifecycleOwner) {
            listAdapter.setList(it)
        }

        viewModel.getWishes()

        return binding.root
    }
}