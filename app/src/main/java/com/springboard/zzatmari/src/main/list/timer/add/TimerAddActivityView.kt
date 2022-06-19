package com.springboard.zzatmari.src.main.list.timer.add

import com.springboard.zzatmari.src.main.list.timer.add.models.PostTimeAddResponse

interface TimerAddActivityView {
    fun onPostTimerSuccess(response: PostTimeAddResponse)

    fun onPostTimerFailure(message: String)
}