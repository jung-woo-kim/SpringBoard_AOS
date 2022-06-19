package com.springboard.zzatmari.src.main.stat

import com.springboard.zzatmari.src.main.stat.models.GetCalendarResponse

interface StatFragmentView {
    fun onGetCalendarSuccess(response: GetCalendarResponse)

    fun onGetCalendarFailure(message: String)




}