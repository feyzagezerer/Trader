package com.fey.trader.ui.portfolio


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fey.trader.R
import com.fey.trader.data.model.Item

import com.fey.trader.databinding.FragmentPortfolioBinding
import com.fey.trader.utils.extensions.toast

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class PortfolioFragment : Fragment(
) {

    private val portfolioViewModel: PortfolioFragmentViewModel by viewModels()
    private lateinit var binding: FragmentPortfolioBinding
private lateinit var portfolioAdapter: PortfolioAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portfolio, container, false)
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            viewModel = portfolioViewModel
        }
        portfolioAdapter = PortfolioAdapter()
        binding.recyclerStock.apply {
        layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter=portfolioAdapter}
        //postponeEnterTransition()

        initObservers()
        return binding.root
    }

    private fun initObservers() {
        portfolioViewModel.stocks.observe(viewLifecycleOwner, Observer { stocks ->
            stocks?.let {
                portfolioAdapter.setStocks(stocks)
            }
        })
        portfolioViewModel.apply {
            stocksSuccess.observe(viewLifecycleOwner) {
            }
            errorMessage.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    toast(it)
                }
            }

        }
        portfolioViewModel.getStocks()
    }


}
