package com.springboard.zzatmari.src.main.list.timer.list

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.list.ListRetrofitInterface
import com.springboard.zzatmari.src.main.list.timer.list.models.DeleteTimerListResponse
import com.springboard.zzatmari.src.main.list.timer.list.models.GetTimerListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimerListService(val view: TimerListActivityView) {
    fun tryGetTimerListItem(){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.getTimerList().enqueue(object : Callback<GetTimerListResponse>{
            override fun onResponse(call: Call<GetTimerListResponse>, response: Response<GetTimerListResponse>) {
                view.onGetTimerSuccess(response.body() as GetTimerListResponse)
            }

            override fun onFailure(call: Call<GetTimerListResponse>, t: Throwable) {
                view.onGetTimerFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryDelteTimerListItem(timerIdx:Int){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.deleteTimer(timerIdx).enqueue(object : Callback<DeleteTimerListResponse>{
            override fun onResponse(call: Call<DeleteTimerListResponse>, response: Response<DeleteTimerListResponse>) {
                view.onDeleteTimerSuccess(response.body() as DeleteTimerListResponse)
            }

            override fun onFailure(call: Call<DeleteTimerListResponse>, t: Throwable) {
                view.onDeleteTimerFailure(t.message?:"통신 오류")
            }

        })
    }
}