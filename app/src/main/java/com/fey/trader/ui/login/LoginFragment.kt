package com.fey.trader.ui.login


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fey.trader.R
import com.fey.trader.core.BaseFragment
import com.fey.trader.databinding.FragmentLoginBinding
import com.fey.trader.utils.UserAuthResult
import com.fey.trader.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(
    R.layout.fragment_login,
    LoginViewModel::class.java,
) {
    private lateinit var sharedPreferences: SharedPreferences
    private val loginViewModel : LoginViewModel by viewModels()
    @SuppressLint("SuspiciousIndentation")
    override fun init() {
        super.init()
        sharedPreferences = context?.getSharedPreferences("STATE", Context.MODE_PRIVATE)!!
         showItems()
        initObservers()
            binding.loginButton.setOnClickListener {

                val username = binding.usernameEditText.text.toString()
                val password = binding.passwordText.text.toString()

                if (checkUsernameAndPassword(
                        username,
                        password
                    )
                ) {
                   loginViewModel.login(
                       username,
                        password
                    ).also {
                        addSharedPreferences(username, password)
                    }
                   // navigate(R.id.portfolioFragment)
                //      navigate(R.id.action_loginFragment_to_portfolioFragment)
                } else {
                    toast("There are places left blank")
                }
            }

    }

private fun checkUsernameAndPassword( username: String,
                                      password: String): Boolean {
    val check = when {
        username.trim().isEmpty() -> false
        password.trim().isEmpty() -> false
        else -> true
    }
    return check
}

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            loginViewModel.authResult.collect { authResult ->
                when (authResult) {
                    is UserAuthResult.UserAuthorized -> {
                        showProgressBar()
                        navigate(R.id.portfolioFragment)
                        toast("UserAuthorized")
                    }
                    is UserAuthResult.UserUnAuthorized -> {
                        clearSharedPreferences()
                        toast("UserUnAuthorized")
                    }
                    is UserAuthResult.UserUnknownError -> {
                        clearSharedPreferences()
                        toast("UserUnknownError")
                    }
                }
            }
        }}
private fun addSharedPreferences(username: String,password: String){
    val editor = sharedPreferences.edit()
    editor.putString("state","user")
    editor.putString("username",username)
    editor.putString("password",password)
    editor.commit()
}
    private fun clearSharedPreferences(){
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
    private fun showItems(){
        with(binding){
            loginButton.visibility = View.VISIBLE
            passwordText.visibility = View.VISIBLE
            //signUpPageTextView.visibility = View.VISIBLE
            usernameEditText.visibility = View.VISIBLE
            forgetPasswordTextView.visibility = View.VISIBLE
            welcomeTV.visibility = View.VISIBLE
            infoTV.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    private fun showProgressBar(){
        with(binding){//progress
            progressBar.visibility = View.VISIBLE
            loginButton.visibility = View.GONE
            passwordText.visibility = View.GONE
        //    signUpPageTextView.visibility = View.GONE
            usernameEditText.visibility = View.GONE
            forgetPasswordTextView.visibility = View.GONE
            welcomeTV.visibility = View.GONE
            infoTV.visibility = View.GONE
        }
}
}