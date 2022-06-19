package com.springboard.zzatmari.src.main.list.models

import com.google.gson.annotations.SerializedName

data class GetListItemResult(
        @SerializedName("listIdx") val listIdx:Int,
        @SerializedName("listItem") val listItem:String,
        @SerializedName("time") val time:Int,
        @SerializedName("goalTime") val goalTime:Int
)