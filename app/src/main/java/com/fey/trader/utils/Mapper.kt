package com.fey.trader.utils


interface Mapper<R, D> {
    fun mapFrom(type: R): D
}
