package com.fey.trader.data.model

import com.google.gson.annotations.SerializedName

data class StocksResponse (
    @SerializedName("Result") val result :Result,
    @SerializedName("Item") val item :List<Item>
    )