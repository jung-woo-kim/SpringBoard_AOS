package com.springboard.zzatmari.src.main.plant



import com.springboard.zzatmari.src.main.plant.models.GetGrowingPlantResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseDetailResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestRequest
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestResponse
import retrofit2.Call
import retrofit2.http.*

interface PlantRetrofitInterface {
    @GET("/users/{userIdx}/seed")
    fun getSeedWareHouse(@Path("userIdx") userIdx:Int): Call<GetSeedWareHouseResponse>
    @GET("/seeds/{seedIdx}")
    fun getSeedWareHouseDetail(@Path("seedIdx") seedIdx:Int): Call<GetSeedWareHouseDetailResponse>
    @PATCH("/plants")
    fun patchPlantOrHarvest(@Body params:PatchPlantOrHarvestRequest):Call<PatchPlantOrHarvestResponse>
    @GET("/plants/growing")
    fun getGrowingPlant(): Call<GetGrowingPlantResponse>
}