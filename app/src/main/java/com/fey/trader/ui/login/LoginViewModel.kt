package com.fey.trader.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fey.trader.core.BaseViewModel
import com.fey.trader.core.Constants.NetworkService.AccountID
import com.fey.trader.core.Constants.NetworkService.ExchangeID
import com.fey.trader.core.Constants.NetworkService.MsgType
import com.fey.trader.core.Constants.NetworkService.OutputType
import com.fey.trader.data.remote.MatriksApi
import com.fey.trader.data.repo.auth.UserRepository
import com.fey.trader.utils.UserAuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.security.auth.callback.Callback


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository,
    private val api: MatriksApi
) : BaseViewModel() {
    //region userInfo
    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    //endregion

     val resultChannel = MutableLiveData<UserAuthResult<Unit>>()

    val resultLoading = MutableLiveData(false)

    fun onLoginClick(){
        Timber.e("onLoginClick")
        if(checkUsernameAndPassword(username.value,password.value)){
            authenticate(username.value,password.value)
        }

    }
  private  fun authenticate(username: String?,password: String?){
        viewModelScope.launch {
            resultLoading.postValue(true)
            if (username != null) {
                if (password != null) {
                    api.login(MsgType,username,password,AccountID,ExchangeID,OutputType)
                        .enqueue(object : Callback)
                }
            }
            resultLoading.postValue(true)
        }
    }

    private fun checkUsernameAndPassword(
        username: String?,
        password: String?
    ): Boolean {
        if(username==null){
            return false
        }
        if(password==null){
            return false
        }
        val check = when {

            username.trim().isEmpty() -> false
            password.trim().isEmpty() -> false
            else -> true
        }
        return check
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
            resultChannel.postValue(result)
        }
    }

}
