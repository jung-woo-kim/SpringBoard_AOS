package com.springboard.zzatmari.src.main.plant.seeds


import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseResponse

interface SeedWareHouseFragmentView {
    fun onGetSeedWareHouseSuccess(response: GetSeedWareHouseResponse)

    fun onGetSeedWareHouseFailure(message: String)


}