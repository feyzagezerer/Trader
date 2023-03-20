package com.fey.trader.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
@SerializedName("DefaultAccount") val accountID : String,
@SerializedName("Result") val result :Result,

)