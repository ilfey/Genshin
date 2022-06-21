package com.josty.genshin.list.ui.ListViewModel

import androidx.lifecycle.ViewModel

abstract class ListViewModel : ViewModel() {

    abstract fun onRefresh()
}
