package com.fey.trader.data.repo.stock
import com.fey.trader.data.remote.MatriksApi

import javax.inject.Inject


class StockRepository @Inject constructor(
    private val matriksApi: MatriksApi,
) {
    suspend fun getAllStocks(MsgType: String,  username: String,  password: String,  AccountID: String,  ExchangeID: String,OutputType: String) =
        matriksApi.getStocks(MsgType, username, password,AccountID, ExchangeID,OutputType)


}
