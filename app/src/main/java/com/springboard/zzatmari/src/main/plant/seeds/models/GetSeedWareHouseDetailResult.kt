package com.springboard.zzatmari.src.main.plant.seeds.models

import com.google.gson.annotations.SerializedName

data class GetSeedWareHouseDetailResult(
        @SerializedName("mySunlight") val mySunlight:Int,
        @SerializedName("seedIdx") val seedIdx:Int,
        @SerializedName("seedName") val seedName:String,
        @SerializedName("seedImgUrl") val seedImgUrl:String,
        @SerializedName("sunlight") val sunlight:Int,
        @SerializedName("floweringTime") val floweringTime:Int,
        @SerializedName("growthTime") val growthTime:Int,
        @SerializedName("reward") val reward:Int,
        @SerializedName("quantity") val quantity:Int


)