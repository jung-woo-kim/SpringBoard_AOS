package com.springboard.zzatmari.src.main.plant.seeds

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.plant.PlantRetrofitInterface
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseDetailResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestRequest
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeedWareHouseDetailService(val view: SeedWareHouseDetailFragmentView) {
    fun tryGetSeedWareHouseDetail(seedIdx: Int){
        val signRetrofitInterface= Application.sRetrofit.create(PlantRetrofitInterface::class.java)
        signRetrofitInterface.getSeedWareHouseDetail(seedIdx).enqueue(object : Callback<GetSeedWareHouseDetailResponse>{
            override fun onResponse(call: Call<GetSeedWareHouseDetailResponse>, response: Response<GetSeedWareHouseDetailResponse>) {
                view.onGetSeedWareHouseDetailSuccess(response.body() as GetSeedWareHouseDetailResponse)
            }

            override fun onFailure(call: Call<GetSeedWareHouseDetailResponse>, t: Throwable) {
                view.onGetSeedWareHouseDetailFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPatchPlantOrHarvest(patchPlantOrHarvestRequest: PatchPlantOrHarvestRequest){
        val signRetrofitInterface= Application.sRetrofit.create(PlantRetrofitInterface::class.java)
        signRetrofitInterface.patchPlantOrHarvest(patchPlantOrHarvestRequest).enqueue(object : Callback<PatchPlantOrHarvestResponse>{
            override fun onResponse(call: Call<PatchPlantOrHarvestResponse>, response: Response<PatchPlantOrHarvestResponse>) {
                view.onPatchPlantOrHarvestSuccess(response.body() as PatchPlantOrHarvestResponse)
            }

            override fun onFailure(call: Call<PatchPlantOrHarvestResponse>, t: Throwable) {
                view.onPatchPlantOrHarvestFailure(t.message?:"통신 오류")
            }

        })
    }

}