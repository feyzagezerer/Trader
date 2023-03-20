package com.fey.trader.ui.portfolio

import androidx.databinding.ObservableField
import com.fey.trader.core.BaseViewModel
import com.fey.trader.data.model.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


    @HiltViewModel
    class PortfolioItemViewModel @Inject internal constructor() : BaseViewModel() {
        var item = ObservableField<ListItem>()
    }
