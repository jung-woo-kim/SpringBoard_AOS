package com.springboard.zzatmari.src.main.plant.seeds.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PatchPlantOrHarvestResponse(
        @SerializedName("result")val result:PatchPlantOrHarvestResult
        ):BaseResponse()



