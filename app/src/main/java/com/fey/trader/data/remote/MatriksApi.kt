package com.fey.trader.data.remote

import com.fey.trader.data.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface MatriksApi {

    @GET("Integration.aspx")
    fun login(
        @Query("MsgType") MsgType: String,
        @Query("CustomerNo") CustomerNo: String,
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("AccountID") AccountID: String,
        @Query("ExchangeID") ExchangeID: String,
        @Query("OutputType") OutputType: String
    ): Observable<LoginResponse>


    @POST("Integration.aspx?")
    @FormUrlEncoded
    suspend fun getStocks(
        @Field("MsgType") MsgType: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("AccountID") AccountID: String,
        @Field("ExchangeID") ExchangeID: String,
        @Field("OutputType") OutputType: String
    ): Call<List<Stocks>>
}