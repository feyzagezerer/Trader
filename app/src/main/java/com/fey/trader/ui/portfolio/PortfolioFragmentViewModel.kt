package com.fey.trader.ui.portfolio

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fey.trader.core.BaseViewModel
import com.fey.trader.core.Constants
import com.fey.trader.data.model.Item

import com.fey.trader.data.model.StocksResponse
import com.fey.trader.data.remote.MatriksApi
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
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
    var totalAmount = 0.0
    var totalAmountString = MutableLiveData<String>()


    init{
        getStocks()
    }
    @WorkerThread
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
                        })
                }
            }
            resultLoading.postValue(true)
        }
    }
    @WorkerThread
    private fun checkResult(stocksResponse: StocksResponse){
        if(stocksResponse.result.state){
            resultLoading.postValue(false)
            multiplicationResult(stocksResponse.item)
            _stocks.postValue (stocksResponse.item)
                stocksSuccess.postValue(true)
        }else{
            resultLoading.postValue(false)
            errorMessage.postValue(stocksResponse.result.description)
        }
    }
    @WorkerThread
    private fun multiplicationResult(item: List<Item>) {
        for(i in item.indices){
           totalAmount += item[i].amount
        }
        totalAmountString.postValue(totalAmount.toString())

        // amountList.postValue ( amount)

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