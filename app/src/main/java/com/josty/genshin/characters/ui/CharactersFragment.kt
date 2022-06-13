package com.josty.genshin.characters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.josty.genshin.characters.adapter.CharactersAdapter
import com.josty.genshin.databinding.FragmentCharactersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {
    private val viewModel by viewModel<CharactersViewModel>()
    private lateinit var adapter: CharactersAdapter
    private lateinit var binding: FragmentCharactersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)

        adapter = CharactersAdapter()
        binding.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recycler.adapter = adapter
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }
        return binding.root
    }
}