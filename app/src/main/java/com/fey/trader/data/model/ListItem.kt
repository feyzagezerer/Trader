package com.fey.trader.data.model

import com.facebook.stetho.json.annotation.JsonProperty
import com.google.gson.annotations.JsonAdapter


data class ListItem(
    @JsonProperty
    val symbol: String?,
    @JsonProperty
    val Qty_T2: Int?,
    @JsonProperty
    val LastPx: Int?,

)