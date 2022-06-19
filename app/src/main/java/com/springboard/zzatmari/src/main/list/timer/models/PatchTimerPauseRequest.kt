package com.springboard.zzatmari.src.main.list.timer.models

import com.google.gson.annotations.SerializedName

data class PatchTimerPauseRequest (
        @SerializedName("min")val min:Int,
        @SerializedName("sec")val sec:Int
        )

