package com.fey.trader.data.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("State")
    val state: Boolean,

    @SerializedName("Code")
    val code: Long,

    @SerializedName("Description")
    val description: String,

    @SerializedName("SessionKey")
    val sessionKey: String,

    @SerializedName("Duration")
    val duration: Long,

    @SerializedName("MsgType")
    val msgType: String,

    @SerializedName("Timestamp")
    val timestamp: Any? = null,

    @SerializedName("ClOrdID")
    val clOrdID: String,

    @SerializedName("Reserved")
    val reserved: String,

    @SerializedName("V")
    val v: String,

    @SerializedName("M")
    val m: String
) 