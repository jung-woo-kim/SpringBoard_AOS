package com.springboard.zzatmari.src.main.plant.models

import com.google.gson.annotations.SerializedName

data class GetGrowingPlantResult(
        @SerializedName("plantIdx") val plantIdx:Int,
        @SerializedName("seedIdx") val seedIdx:Int,
        @SerializedName("seedImgUrl") val seedImgUrl:String,
        @SerializedName("plantImgUrl") val plantImgUrl:String,
        @SerializedName("growthStage") val growthStage:Int,
        @SerializedName("executionTime") val executionTime:Int,
        @SerializedName("floweringTime") val floweringTime:Int,
        @SerializedName("status") val status:Int

)