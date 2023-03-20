package com.fey.trader.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class ListItem(

    @Json(name = "symbol")
    val symbol: String?,

    @Json(name = "Qty_T2")
    val Qty_T2: Int?,

    @Json(name = "LastPx")
    val LastPx: Int?,

) : Parcelable {
}
