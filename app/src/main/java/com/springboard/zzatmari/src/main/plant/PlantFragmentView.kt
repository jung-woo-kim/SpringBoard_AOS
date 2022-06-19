package com.springboard.zzatmari.src.main.plant


import com.springboard.zzatmari.src.main.plant.models.GetGrowingPlantResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseDetailResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestResponse


interface PlantFragmentView {
    fun onGetGrowingPlantSuccess(response: GetGrowingPlantResponse)

    fun onGetGrowingPlantFailure(message: String)

    fun onPatchPlantOrHarvestSuccess(response: PatchPlantOrHarvestResponse)

    fun onPatchPlantOrHarvestFailure(message: String)

    fun onGetSeedWareHouseDetailSuccess(response: GetSeedWareHouseDetailResponse)

    fun onGetSeedWareHouseDetailFailure(message: String)


}