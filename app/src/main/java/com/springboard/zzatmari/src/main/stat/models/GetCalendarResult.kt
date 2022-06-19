package com.springboard.zzatmari.src.main.stat.models

import com.google.gson.annotations.SerializedName

data class GetCalendarResult(
        @SerializedName("continuousDay") val continuousDay:Int,
        @SerializedName("statList") val statList:ArrayList<DayList>

)