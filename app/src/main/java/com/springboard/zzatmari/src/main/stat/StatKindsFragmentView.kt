package com.springboard.zzatmari.src.main.stat


import com.springboard.zzatmari.src.main.stat.models.GetCalendarListResponse


interface StatKindsFragmentView {

    fun onGetCalendarListSuccess(response: GetCalendarListResponse)

    fun onGetCalendarListFailure(message: String)


}