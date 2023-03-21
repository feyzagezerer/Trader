package com.fey.trader.data.model

import com.facebook.stetho.json.annotation.JsonProperty
import com.google.gson.annotations.JsonAdapter


data class ListItem(
    @JsonProperty
    val symbol: String?,
    @JsonProperty
    val qty_T2: Double?,
    @JsonProperty
    val lastPx: Double?,

)