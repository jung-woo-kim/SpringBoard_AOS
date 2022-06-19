package com.springboard.zzatmari.src.main.stat.models

import com.google.gson.annotations.SerializedName

data class GetCalendarListResult(
        @SerializedName("digitalDetoxPercent") val digitalDetoxPercent:Int,
        @SerializedName("selfDevelopmentPercent") val selfDevelopmentPercent:Int,
        @SerializedName("digitalDetoxTime") val digitalDetoxTime:Int,
        @SerializedName("selfDevelopmentTime") val selfDevelopmentTime:Int,
        @SerializedName("digitalDetox") val digitalDetox:ArrayList<CalendarListItem>,
        @SerializedName("selfDevelopment") val selfDevelopment:ArrayList<CalendarListItem>
)