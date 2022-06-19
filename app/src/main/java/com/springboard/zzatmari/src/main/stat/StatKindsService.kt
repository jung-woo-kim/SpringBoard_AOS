package com.springboard.zzatmari.src.main.stat


import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.stat.models.GetCalendarListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StatKindsService(val view: StatKindsFragmentView) {

    fun tryGetCalendarList(type:String,year:Int,month:Int,day:Int){
        val signRetrofitInterface= Application.sRetrofit.create(StatRetrofitInterface::class.java)
        signRetrofitInterface.getCalendarList(type,year,month,day).enqueue(object : Callback<GetCalendarListResponse>{
            override fun onResponse(call: Call<GetCalendarListResponse>, response: Response<GetCalendarListResponse>) {
                view.onGetCalendarListSuccess(response.body() as GetCalendarListResponse)
            }

            override fun onFailure(call: Call<GetCalendarListResponse>, t: Throwable) {
                view.onGetCalendarListFailure(t.message?:"통신 오류")
            }

        })
    }

}