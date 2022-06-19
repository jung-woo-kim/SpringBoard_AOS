package com.springboard.zzatmari.src.main.mypage.account

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.initial_setting.InitialRetrofitInterface
import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeRequest
import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StartTimeChangeService(val view: StartTimeChangeActivityView) {
    fun tryPostInitialTime(postInitialTimeRequest: PostInitialTimeRequest){
        val signRetrofitInterface= Application.sRetrofit.create(InitialRetrofitInterface::class.java)
        signRetrofitInterface.postInitialTime(postInitialTimeRequest).enqueue(object : Callback<PostInitialTimeResponse>{
            override fun onResponse(call: Call<PostInitialTimeResponse>, response: Response<PostInitialTimeResponse>) {
                view.onPostTimeSuccess(response.body() as PostInitialTimeResponse)
            }

            override fun onFailure(call: Call<PostInitialTimeResponse>, t: Throwable) {
                view.onPostTimeFailure(t.message?:"통신 오류")
            }

        })
    }


}