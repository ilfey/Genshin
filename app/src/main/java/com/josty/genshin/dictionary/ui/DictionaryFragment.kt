package com.josty.genshin.dictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josty.genshin.dictionary.adapter.DictionaryAdapter
import com.josty.genshin.list.ui.ListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DictionaryFragment : ListFragment() {

    override val viewModel by viewModel<DictionaryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val listAdapter = DictionaryAdapter()
        binding.recycler.adapter = listAdapter

        viewModel.list.observe(viewLifecycleOwner) {
            listAdapter.setList(it)
        }

        viewModel.getDictionary()

        return binding.root
    }
}