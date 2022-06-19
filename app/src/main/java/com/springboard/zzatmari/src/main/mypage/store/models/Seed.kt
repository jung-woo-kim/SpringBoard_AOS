package com.springboard.zzatmari.src.main.mypage.store.models

import com.google.gson.annotations.SerializedName

data class Seed(
        @SerializedName("seedImgUrl") val seedImgUrl:String,
        @SerializedName("seedIdx") val seedIdx:Int,
        @SerializedName("seedName") val seedName:String,
        @SerializedName("sunlight") val sunlight:Int
        )



