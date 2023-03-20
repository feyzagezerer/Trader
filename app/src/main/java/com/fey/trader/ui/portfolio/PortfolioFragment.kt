package com.fey.trader.ui.portfolio


import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fey.trader.R
import com.fey.trader.core.BaseFragment
import com.fey.trader.databinding.FragmentPortfolioBinding

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PortfolioFragment : BaseFragment<PortfolioFragmentViewModel, FragmentPortfolioBinding>(
    R.layout.fragment_portfolio,
    PortfolioFragmentViewModel::class.java,
) {
    private var isLoading = false
    private var isLastPage = false
    private var isScrolling = false
    override fun init() {
        super.init()


        //binding.recyclerStock.adapter = adapter
        binding.recyclerStock.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        postponeEnterTransition()
        binding.recyclerStock.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }


    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        isLoading = true
    }
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        isLoading = false
    }

    }
