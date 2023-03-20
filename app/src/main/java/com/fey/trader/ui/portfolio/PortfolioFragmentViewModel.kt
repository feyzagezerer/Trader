package com.fey.trader.ui.portfolio

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fey.trader.core.BaseViewModel
import com.fey.trader.data.model.Stocks
import com.fey.trader.data.repo.stock.StockRepository
import com.fey.trader.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class PortfolioFragmentViewModel @Inject internal constructor(
    private val stockRepository: StockRepository,
    var sharedPreferences: SharedPreferences
) : BaseViewModel() {
    val stocks: MutableLiveData<Resource<Stocks>> = MutableLiveData()
    private var stocksResponse: Stocks? = null

    private fun getAllStocks(MsgType: String,  username: String,  password: String,  AccountID: String,  ExchangeID: String,OutputType: String) = viewModelScope.launch {
        stocks.postValue(Resource.loading())
        val response = stockRepository.getAllStocks(MsgType, username, password,AccountID, ExchangeID,OutputType)
        stocks.postValue(handleArticlesResponse(response))
    }

    private fun handleArticlesResponse(response: Call<List<Stocks>>): Resource<Stocks> {

    }

}