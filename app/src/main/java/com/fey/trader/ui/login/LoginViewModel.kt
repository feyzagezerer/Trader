package com.fey.trader.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fey.trader.core.BaseViewModel
import com.fey.trader.data.repo.auth.UserRepository
import com.fey.trader.utils.UserAuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {
    var navigatePortfolio = false
    private val resultChannel = Channel<UserAuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()

    val resultLoading = MutableLiveData<Boolean>()

    init {
        authenticate()
    }
    fun authenticate(){
        viewModelScope.launch {
            resultLoading.value = true
           // val result = repository.authenticate()
            //resultChannel.send(result)
            resultLoading.value = false
        }
    }
    fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            val result = repository.login(
                username = username,
                password = password
            )
            resultChannel.send(result)
        }
    }

}
