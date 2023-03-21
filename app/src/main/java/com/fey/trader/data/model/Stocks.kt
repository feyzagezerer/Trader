package com.fey.trader.data.model

import com.google.gson.annotations.SerializedName

data class Stocks (
    val symbol: String,
    val qty_T2: Int,
    val lastPx: Int

)