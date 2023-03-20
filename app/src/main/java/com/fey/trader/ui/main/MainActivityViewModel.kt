package com.fey.trader.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.fey.trader.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject  constructor() : ViewModel()  {
    var toolbarTitle: ObservableField<String> = ObservableField()
}
