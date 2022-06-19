package com.springboard.zzatmari.src.main.plant.seeds


import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseDetailResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestResponse


interface SeedWareHouseDetailFragmentView {
    fun onGetSeedWareHouseDetailSuccess(response: GetSeedWareHouseDetailResponse)

    fun onGetSeedWareHouseDetailFailure(message: String)

    fun onPatchPlantOrHarvestSuccess(response: PatchPlantOrHarvestResponse)

    fun onPatchPlantOrHarvestFailure(message: String)


}