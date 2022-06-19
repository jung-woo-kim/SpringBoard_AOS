package com.springboard.zzatmari.src.initial_setting.list

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.initial_setting.InitialRetrofitInterface
import com.springboard.zzatmari.src.initial_setting.list.models.PostInitialListRequest
import com.springboard.zzatmari.src.initial_setting.list.models.PostInitialListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InitialListService(val view: InitialListActivityView) {
    fun tryPostInitialTime(postInitialListRequest: PostInitialListRequest){
        val signRetrofitInterface= Application.sRetrofit.create(InitialRetrofitInterface::class.java)
        signRetrofitInterface.postInitialList(postInitialListRequest).enqueue(object : Callback<PostInitialListResponse>{
            override fun onResponse(call: Call<PostInitialListResponse>, response: Response<PostInitialListResponse>) {
                view.onPostListSuccess(response.body() as PostInitialListResponse)
            }

            override fun onFailure(call: Call<PostInitialListResponse>, t: Throwable) {
                view.onPostListFailure(t.message?:"통신 오류")
            }

        })
    }
}