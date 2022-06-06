package com.josty.genshin.wishes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josty.genshin.R
import com.josty.genshin.databinding.FragmentWishesBinding

class WishesFragment : Fragment() {
    private lateinit var binding: FragmentWishesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWishesBinding.inflate(layoutInflater)
        return binding.root
    }
}