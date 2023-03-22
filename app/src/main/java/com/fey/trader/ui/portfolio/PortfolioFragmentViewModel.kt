package com.fey.trader.ui.portfolio

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fey.trader.core.BaseViewModel
import com.fey.trader.core.Constants
import com.fey.trader.data.model.Item
import com.fey.trader.data.model.LoginResponse

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
    private val _stocks = MutableLiveData<List<Item>>()
val stocks: LiveData<List<Item>> = _stocks
    val resultLoading = MutableLiveData(false)
    val stocksSuccess = MutableLiveData(false)
    val errorMessage = MutableLiveData<String>()
    var multiplicationResult = mutableListOf<Double>()

    init{
        getStocks()
    }
      fun getStocks(){
        viewModelScope.launch {
     val username  =  sharedPreferences.getString("username", "default")
     val password  =  sharedPreferences.getString("password", "default")
     val accountID  =  sharedPreferences.getString("accountID", "default")

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
                            Timber.e("stocksResponse %s %s %s",it.item[0].symbol,it.item[1].symbol,it.result.description)
                            checkResult(it)
                            multiplicationResult(it.item)
                        })
                }
            }
            resultLoading.postValue(true)
        }
    }
    private fun checkResult(stocksResponse: StocksResponse){
        if(stocksResponse.result.state){
            resultLoading.postValue(false)
            _stocks.postValue (stocksResponse.item)
                stocksSuccess.postValue(true)
        }else{
            resultLoading.postValue(false)
            errorMessage.postValue(stocksResponse.result.description)
        }
    }
    private fun multiplicationResult(item: List<Item>) {
var totalAmount = 0.0
        for(i in 0 until item.size){
            Timber.e("stocksResponse %s  %s  %s",item[i].qtyT2,item[i].lastPx,item[i].qtyT2 * item[i].lastPx)
            var amount = item[i].qtyT2 * item[i].lastPx
            totalAmount += amount
            Timber.e("stocksResponse %s   %s",amount,totalAmount)

        }
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