package com.springboard.zzatmari.src.main.plant.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetGrowingPlantResponse(
        @SerializedName("result")val result: GetGrowingPlantResult
        ):BaseResponse()



