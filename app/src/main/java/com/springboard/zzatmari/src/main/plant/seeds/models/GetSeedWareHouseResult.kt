package com.springboard.zzatmari.src.main.plant.seeds.models

import com.google.gson.annotations.SerializedName

data class GetSeedWareHouseResult(
        @SerializedName("seedIdx") val seedIdx:Int,
        @SerializedName("seedName") val seedName:String,
        @SerializedName("seedImgUrl") val seedImgUrl:String
)