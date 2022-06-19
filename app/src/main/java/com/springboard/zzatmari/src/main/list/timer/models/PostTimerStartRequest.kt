package com.springboard.zzatmari.src.main.list.timer.models

import com.google.gson.annotations.SerializedName

data class PostTimerStartRequest (
        @SerializedName("listIdx")val listIdx:Int,
        @SerializedName("timerIdx")val timerIdx:Int
        )

