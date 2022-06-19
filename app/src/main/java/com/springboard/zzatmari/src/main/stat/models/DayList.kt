package com.springboard.zzatmari.src.main.stat.models

import com.google.gson.annotations.SerializedName

data class DayList(
        @SerializedName("day") val day:Int,
        @SerializedName("percent") val percent:Int

)