package com.fey.trader.ui.login


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.fey.trader.R
import com.fey.trader.core.BaseFragment
import com.fey.trader.databinding.FragmentLoginBinding
import com.fey.trader.utils.UserAuthResult
import com.fey.trader.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment(
) {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            viewModel = loginViewModel
        }
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        loginViewModel.apply {
            loginSuccess.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(R.id.action_loginFragment_to_portfolioFragment)
                }
            }
            errorMessage.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    toast(it)
                }
            }
        }
    }



}