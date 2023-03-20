package com.fey.trader.data.remote

import com.fey.trader.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface MatriksApi {

    @POST("Integration.aspx?")
    @FormUrlEncoded
      fun login(@Field("MsgType") MsgType: String, @Field("username") username: String, @Field("password") password: String,
                        @Field("AccountID") AccountID: String,@Field("ExchangeID") ExchangeID: String,@Field("OutputType") OutputType: String ): Call<AccountIDResponse>

    @POST("Integration.aspx?")
    suspend fun getUserData(
        @Body request: UserRequest
    ): AccountIDResponse


    @POST("Integration.aspx?")
    @FormUrlEncoded
   suspend fun getStocks(@Field("MsgType") MsgType: String, @Field("username") username: String, @Field("password") password: String,
                         @Field("AccountID") AccountID: String,@Field("ExchangeID") ExchangeID: String,@Field("OutputType") OutputType: String):  Call<List<Stocks>>
}