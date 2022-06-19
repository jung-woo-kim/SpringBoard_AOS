package com.springboard.zzatmari.src.main.list.timer.list

import com.springboard.zzatmari.src.main.list.timer.list.models.DeleteTimerListResponse
import com.springboard.zzatmari.src.main.list.timer.list.models.GetTimerListResponse

interface TimerListActivityView {
    fun onGetTimerSuccess(response: GetTimerListResponse)

    fun onGetTimerFailure(message: String)

    fun onDeleteTimerSuccess(response: DeleteTimerListResponse)

    fun onDeleteTimerFailure(message: String)
}