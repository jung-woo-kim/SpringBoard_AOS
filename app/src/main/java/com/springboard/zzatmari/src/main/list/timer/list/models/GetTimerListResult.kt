package com.springboard.zzatmari.src.main.list.timer.list.models

import com.google.gson.annotations.SerializedName

data class GetTimerListResult(
        @SerializedName("timerIdx") val timerIdx:Int,
        @SerializedName("hour") val hour:Int,
        @SerializedName("minute") val minute:Int,
        @SerializedName("time") val time:String
)