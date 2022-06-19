package com.springboard.zzatmari.src.main.plant.seeds

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.plant.PlantRetrofitInterface
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeedWareHouseService(val view: SeedWareHouseFragmentView) {
    fun trySeedWareHouse(userIdx: Int){
        val signRetrofitInterface= Application.sRetrofit.create(PlantRetrofitInterface::class.java)
        signRetrofitInterface.getSeedWareHouse(userIdx).enqueue(object : Callback<GetSeedWareHouseResponse>{
            override fun onResponse(call: Call<GetSeedWareHouseResponse>, response: Response<GetSeedWareHouseResponse>) {
                view.onGetSeedWareHouseSuccess(response.body() as GetSeedWareHouseResponse)
            }

            override fun onFailure(call: Call<GetSeedWareHouseResponse>, t: Throwable) {
                view.onGetSeedWareHouseFailure(t.message?:"통신 오류")
            }

        })
    }

}