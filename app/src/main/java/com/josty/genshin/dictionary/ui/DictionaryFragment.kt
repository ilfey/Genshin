package com.josty.genshin.dictionary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josty.genshin.databinding.FragmentDictionaryBinding
import com.josty.genshin.dictionary.adapter.DictionaryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DictionaryFragment : Fragment() {
    private val viewModel by viewModel<DictionaryViewModel>()
    private lateinit var adapter: DictionaryAdapter
    private lateinit var binding: FragmentDictionaryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDictionaryBinding.inflate(layoutInflater)

        adapter = DictionaryAdapter()
        binding.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = adapter
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
        viewModel.getDictionary()
        return binding.root
    }
}