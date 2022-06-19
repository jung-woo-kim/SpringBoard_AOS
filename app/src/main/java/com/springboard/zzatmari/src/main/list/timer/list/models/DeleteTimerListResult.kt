package com.springboard.zzatmari.src.main.list.timer.list.models

import com.google.gson.annotations.SerializedName

data class DeleteTimerListResult(
        @SerializedName("timerIdx") val timerIdx:Int
)