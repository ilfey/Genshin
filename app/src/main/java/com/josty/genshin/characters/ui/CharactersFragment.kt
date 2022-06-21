package com.josty.genshin.characters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josty.genshin.characters.adapter.CharactersAdapter
import com.josty.genshin.list.ui.ListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : ListFragment() {

    override val viewModel by viewModel<CharactersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val listAdapter = CharactersAdapter()
        binding.recycler.adapter = listAdapter

        viewModel.list.observe(viewLifecycleOwner) {
            listAdapter.setList(it)
        }

        viewModel.getCharacters()

        return binding.root
    }
}