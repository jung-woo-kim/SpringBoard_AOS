package com.springboard.zzatmari.src.main.mypage.store.models

import com.google.gson.annotations.SerializedName

data class GetSeedStoreResult(
        @SerializedName("mySunlight") val mySunlight:Int,
        @SerializedName("seedList") val seedList:ArrayList<Seed>

)