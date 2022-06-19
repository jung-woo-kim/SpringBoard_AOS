package com.springboard.zzatmari.src.main.plant.seeds.models

import com.google.gson.annotations.SerializedName

data class PatchPlantOrHarvestRequest (
        @SerializedName("seedIdx")val seedIdx:Int,
        @SerializedName("type")val type:Int
        )

