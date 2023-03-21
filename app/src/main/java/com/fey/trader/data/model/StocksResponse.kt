package com.fey.trader.data.model

import com.google.gson.annotations.SerializedName

data class StocksResponse (
   // @SerializedName("Symbol") val symbol : String,
   // @SerializedName("Qty_T2") val qty_T2 :String,
  //  @SerializedName("LastPx") val lastPx :String,
    @SerializedName("Result") val result :Result,
    @SerializedName("Item") val item :List<Item>,
    )