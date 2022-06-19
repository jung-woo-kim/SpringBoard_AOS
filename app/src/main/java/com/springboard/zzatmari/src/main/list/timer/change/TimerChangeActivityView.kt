package com.springboard.zzatmari.src.main.list.timer.change

import com.springboard.zzatmari.src.main.list.timer.change.models.PatchTimeChangeResponse

interface TimerChangeActivityView {
    fun onPatchTimerChangeSuccess(response: PatchTimeChangeResponse)

    fun onPatchTimerChangeFailure(message: String)
}