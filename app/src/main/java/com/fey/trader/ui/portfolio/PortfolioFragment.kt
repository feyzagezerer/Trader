package com.fey.trader.ui.portfolio


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fey.trader.R
import com.fey.trader.core.BaseFragment
import com.fey.trader.databinding.FragmentLoginBinding
import com.fey.trader.databinding.FragmentPortfolioBinding
import com.fey.trader.ui.login.LoginViewModel
import com.fey.trader.utils.extensions.toast

import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class PortfolioFragment : Fragment(
) {

    private val portfolioViewModel: PortfolioFragmentViewModel by viewModels()
    private lateinit var binding: FragmentPortfolioBinding

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
        binding.recyclerStock.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        postponeEnterTransition()

        initObservers()
        return binding.root
    }

    private fun initObservers() {
        portfolioViewModel.apply {
            stocksSuccess.observe(viewLifecycleOwner) {
toast("GELDİLER")
            }
            errorMessage.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    toast(it)
                }
            }
        }
    }



}
