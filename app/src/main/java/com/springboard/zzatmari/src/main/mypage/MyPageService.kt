package com.springboard.zzatmari.src.main.mypage

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.mypage.models.GetPlantResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageService(val view: MyPageFragmentView) {
    fun tryGetPlant(){
        val signRetrofitInterface= Application.sRetrofit.create(MyPageRetrofitInterface::class.java)
        signRetrofitInterface.getPlant().enqueue(object : Callback<GetPlantResponse>{
            override fun onResponse(call: Call<GetPlantResponse>, response: Response<GetPlantResponse>) {
                view.onGetPlantSuccess(response.body() as GetPlantResponse)
            }

            override fun onFailure(call: Call<GetPlantResponse>, t: Throwable) {
                view.onGetPlantFailure(t.message?:"통신 오류")
            }

        })
    }


}