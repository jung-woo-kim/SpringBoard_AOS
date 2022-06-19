package com.springboard.zzatmari.src.main.list.timer.models

import com.google.gson.annotations.SerializedName

data class GetTimerNowResult(
        @SerializedName("min") val min:Int,
        @SerializedName("sec") val sec:Int,
        @SerializedName("status") val status:Int
)