package com.springboard.zzatmari.src.main.stat.models

import com.google.gson.annotations.SerializedName

data class CalendarListItem(
        @SerializedName("listItem") val listItem:String,
        @SerializedName("time") val time:Int
)