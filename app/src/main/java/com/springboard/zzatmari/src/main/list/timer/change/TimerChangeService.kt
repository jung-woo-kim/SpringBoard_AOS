package com.springboard.zzatmari.src.main.list.timer.change

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.list.ListRetrofitInterface
import com.springboard.zzatmari.src.main.list.timer.change.models.PatchTimeChangeRequest
import com.springboard.zzatmari.src.main.list.timer.change.models.PatchTimeChangeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimerChangeService(val view: TimerChangeActivityView) {
    fun tryTimerListItem(timerIdx:Int,petchTimerChangeRequest: PatchTimeChangeRequest){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.patchTimer(timerIdx,petchTimerChangeRequest).enqueue(object : Callback<PatchTimeChangeResponse>{
            override fun onResponse(call: Call<PatchTimeChangeResponse>, response: Response<PatchTimeChangeResponse>) {
                view.onPatchTimerChangeSuccess(response.body() as PatchTimeChangeResponse)
            }
            override fun onFailure(call: Call<PatchTimeChangeResponse>, t: Throwable) {
                view.onPatchTimerChangeFailure(t.message?:"통신 오류")
            }
        })
    }
}