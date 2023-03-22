package com.fey.trader.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("ProtectionOrderID")
    val protectionOrderID: String,

    @SerializedName("AccountID")
    val accountID: String,

    @SerializedName("RecordDate")
    val recordDate: String,

    @SerializedName("ClosingDate")
    val closingDate: String,

    @SerializedName("Symbol")
    val symbol: String,

    @SerializedName("SymbolID")
    val symbolID: Long,

    @SerializedName("State")
    val state: String,

    @SerializedName("Type")
    val type: String,

    @SerializedName("Qty_T")
    val qtyT: Double,

    @SerializedName("Qty_T1")
    val qtyT1: Double,

    @SerializedName("Qty_T2")
    val qtyT2: Double,

    @SerializedName("Qty_T3")
    val qtyT3: Double,

    @SerializedName("Qty_Long")
    val qtyLong: Double,

    @SerializedName("Qty_Short")
    val qtyShort: Double,

    @SerializedName("Qty_Net")
    val qtyNet: Double,

    @SerializedName("Qty_Available")
    val qtyAvailable: Double,

    @SerializedName("LastPx")
    val lastPx: Double,

    @SerializedName("SettlementPx")
    val settlementPx: Double,

    @SerializedName("OpeningAvgPrice")
    val openingAvgPrice: Double,

    @SerializedName("ClosingAvgPrice")
    val closingAvgPrice: Double,

    @SerializedName("StopPrice")
    val stopPrice: Double,

    @SerializedName("LimitPrice")
    val limitPrice: Double,

    @SerializedName("Amount")
    val amount: Double,

    @SerializedName("AmountShort")
    val amountShort: Double,

    @SerializedName("AmountLong")
    val amountLong: Double,

    @SerializedName("AvgCost")
    val avgCost: Double,

    @SerializedName("DailyCost")
    val dailyCost: Double,

    @SerializedName("PL")
    val pl: Double,

    @SerializedName("PL_Percent")
    val plPercent: Double,

    @SerializedName("Credit")
    val credit: Double,

    @SerializedName("MarginRequired")
    val marginRequired: Double,

    @SerializedName("Swap")
    val swap: Double,

    @SerializedName("DailyPL")
    val dailyPL: Double,

    @SerializedName("DailyPL_Percent")
    val dailyPLPercent: Double,

    @SerializedName("PL_ur")
    val plUr: Double,

    @SerializedName("PL_r")
    val plR: Double,

    @SerializedName("PositionSide")
    val positionSide: Long,

    @SerializedName("ExFields")
    val exFields: String,

    @SerializedName("ExFields2")
    val exFields2: String,

    @SerializedName("PL_MarketPrice")
    val plMarketPrice: Double,

    @SerializedName("SortOrder")
    val sortOrder: Long,

    @SerializedName("FrameNumber")
    val frameNumber: Long,

    @SerializedName("PositionID")
    val positionID: String

)