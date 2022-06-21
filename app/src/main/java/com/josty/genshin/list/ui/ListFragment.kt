package com.josty.genshin.list.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.josty.genshin.databinding.FragmentListBinding
import com.josty.genshin.list.ui.ListViewModel.ListViewModel
import com.josty.genshin.utils.getThemeColor

abstract class ListFragment : Fragment(),
    SwipeRefreshLayout.OnRefreshListener {

    protected abstract val viewModel: ListViewModel

    protected lateinit var binding: FragmentListBinding

    protected var isRefreshEnabling: Boolean = true

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        with(binding.refresh) {
            setProgressBackgroundColorSchemeColor(context.getThemeColor(com.google.android.material.R.attr.colorPrimary))
            setColorSchemeColors(context.getThemeColor(com.google.android.material.R.attr.colorOnPrimary))
            setOnRefreshListener(this@ListFragment)
            isEnabled = this@ListFragment.isRefreshEnabling
        }

        return binding.root
    }

    @CallSuper
    override fun onRefresh() {
        binding.refresh.isRefreshing = true
        viewModel.onRefresh()
        binding.refresh.isRefreshing = false
    }
}