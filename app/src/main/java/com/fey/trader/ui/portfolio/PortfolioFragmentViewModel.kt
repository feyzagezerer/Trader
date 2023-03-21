package com.fey.trader.ui.portfolio

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fey.trader.core.BaseViewModel
import com.fey.trader.core.Constants
import com.fey.trader.data.model.LoginResponse
import com.fey.trader.data.model.Stocks
import com.fey.trader.data.model.StocksResponse
import com.fey.trader.data.remote.MatriksApi
import com.fey.trader.utils.Resource
import com.fey.trader.utils.UserAuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Call
import timber.log.Timber

import javax.inject.Inject

@HiltViewModel
class PortfolioFragmentViewModel @Inject  constructor(
private val sharedPreferences: SharedPreferences,
private val api: MatriksApi
) : BaseViewModel() {

    private val dis = CompositeDisposable()

    val resultLoading = MutableLiveData(false)
    val stocksSuccess = MutableLiveData(false)
    val errorMessage = MutableLiveData<String>()
    init{
        authenticate()
    }
    private  fun authenticate(){
        viewModelScope.launch {
     var       username  =  sharedPreferences.getString("username", "default")
     var       password  =  sharedPreferences.getString("password", "default")
     var       accountID  =  sharedPreferences.getString("accountID", "default")

            if (username != null) {
                if (password != null && accountID != null) {
                    dis.add(api.getStocks(
                        Constants.NetworkService.MsgTypeS,
                        Constants.NetworkService.CustomerNo,
                        username,
                        password,
                        accountID,
                        Constants.NetworkService.ExchangeID,
                        Constants.NetworkService.OutputType
                    )
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .subscribe {
                            Timber.e("stocksResponse %s  %s %s",it.item[0].symbol,it.item[1].symbol,it.result.description)
                            checkResult(it,it.item[0].symbol,it.item[0].qtyT2,it.item[0].lastPx)

                        })
                }
            }
            resultLoading.postValue(true)
        }
    }
    private fun checkResult(stocksResponse: StocksResponse, symbol: String, qty_T2: Double, lastPx: Double){
        if(stocksResponse.result.state){
            addSharedPreferences(symbol,qty_T2,lastPx)
            stocksSuccess.postValue(true)
        }else{
            errorMessage.postValue(stocksResponse.result.description)
        }
    }
    private fun addSharedPreferences(symbol: String, qty_T2: Double, lastPx: Double) {
        val editor = sharedPreferences.edit()
        editor.putString("symbol", symbol)
        editor.putFloat("qty_T2", qty_T2.toFloat())
        editor.putFloat("lastPx", lastPx.toFloat())
        editor.apply()
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