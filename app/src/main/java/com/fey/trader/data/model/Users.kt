package com.fey.trader.data.model

import com.google.gson.annotations.SerializedName

data class Users (
    @SerializedName("MsgType") val MsgType: String="A",
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("AccountID") val AccountID: String="0",
    @SerializedName("ExchangeID") val ExchangeID: String="4",
    @SerializedName("OutputType") val OutputType: String="2",
)