package com.springboard.zzatmari.src.main.stat


import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.stat.models.GetCalendarResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatService(val view: StatFragmentView) {
    fun tryGetCalendar(year:Int,month:Int){
        val signRetrofitInterface= Application.sRetrofit.create(StatRetrofitInterface::class.java)
        signRetrofitInterface.getCalendar(year,month).enqueue(object : Callback<GetCalendarResponse>{
            override fun onResponse(call: Call<GetCalendarResponse>, response: Response<GetCalendarResponse>) {
                view.onGetCalendarSuccess(response.body() as GetCalendarResponse)
            }

            override fun onFailure(call: Call<GetCalendarResponse>, t: Throwable) {
                view.onGetCalendarFailure(t.message?:"통신 오류")
            }

        })
    }


}