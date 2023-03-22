package com.fey.trader.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fey.trader.core.BaseViewModel
import com.fey.trader.core.Constants.NetworkService.AccountID
import com.fey.trader.core.Constants.NetworkService.CustomerNo
import com.fey.trader.core.Constants.NetworkService.ExchangeID
import com.fey.trader.core.Constants.NetworkService.MsgType
import com.fey.trader.core.Constants.NetworkService.OutputType
import com.fey.trader.data.model.LoginResponse
import com.fey.trader.data.remote.MatriksApi
import com.fey.trader.utils.UserAuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val api: MatriksApi
) : BaseViewModel() {
    //region userInfo
    val username = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    //endregion
    private val dis = CompositeDisposable()

     val resultChannel = MutableLiveData<UserAuthResult<Unit>>()

    val resultLoading = MutableLiveData(false)
    val loginSuccess = MutableLiveData(false)
    val errorMessage = MutableLiveData<String>()

    fun onLoginClick(){
        Timber.e("onLoginClick")
        if(checkUsernameAndPassword(username.value,password.value)){
            authenticate(username.value,password.value)
        }


    }
  private  fun authenticate(username: String?,password: String?){
        viewModelScope.launch {

            if (username != null) {
                if (password != null) {

                    dis.add(api.login(MsgType,CustomerNo,username,password,AccountID,ExchangeID,OutputType)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe {
                            Timber.e("loginresponse %s  %s",it.accountID,it.result.description)
                            checkResult(it,username,password, it.accountID)

                        })
                }
            }
            resultLoading.postValue(true)
        }
    }
    private fun checkResult(loginResponse: LoginResponse,username: String, password: String,accountID: String){
        if(loginResponse.result.state){
            addSharedPreferences(username,password,accountID)
            loginSuccess.postValue(true)
        }else{
            errorMessage.postValue(loginResponse.result.description)
        }
    }
    private fun addSharedPreferences(username: String, password: String, accountID: String) {
        val editor = sharedPreferences.edit()
        editor.putString("state", "user")
        editor.putString("username", username)
        editor.putString("password", password)
        editor.putString("accountID", accountID)
        editor.apply()
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
    private fun clearSharedPreferences() {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
        }
    override fun onCleared() {
        clearSharedPreferences()
        dis.dispose()
        super.onCleared()
    }

}
