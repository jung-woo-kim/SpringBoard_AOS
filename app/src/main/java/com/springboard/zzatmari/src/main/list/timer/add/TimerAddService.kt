package com.springboard.zzatmari.src.main.list.timer.add

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.list.ListRetrofitInterface
import com.springboard.zzatmari.src.main.list.timer.add.models.PostTimeAddRequest
import com.springboard.zzatmari.src.main.list.timer.add.models.PostTimeAddResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimerAddService(val viewAdd: TimerAddActivityView) {
    fun tryPostTimerItem(postTimeAddRequest: PostTimeAddRequest){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.postTimer(postTimeAddRequest).enqueue(object : Callback<PostTimeAddResponse>{
            override fun onResponse(call: Call<PostTimeAddResponse>, response: Response<PostTimeAddResponse>) {
                viewAdd.onPostTimerSuccess(response.body() as PostTimeAddResponse)
            }

            override fun onFailure(call: Call<PostTimeAddResponse>, t: Throwable) {
                viewAdd.onPostTimerFailure(t.message?:"통신 오류")
            }

        })
    }
}