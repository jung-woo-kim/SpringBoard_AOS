package com.springboard.zzatmari.src.main.list.timer

import com.springboard.zzatmari.src.main.list.timer.models.*

interface TimerActivityView {
    fun onGetTimerNowSuccess(response: GetTimerNowResponse)

    fun onGetTimerNowFailure(message: String)

    fun onPostTimerStartSuccess(response: PostTimerStartResponse)

    fun onPostTimerStartFailure(message: String)

    fun onPatchTimerPauseSuccess(response: PatchTimerPauseResponse)

    fun onPatchTimerPauseFailure(message: String)

    fun onPatchTimerContinueSuccess(response: PatchTimerContinueResponse)

    fun onPatchTimerContinueFailure(message: String)

    fun onPatchTimerCompleteSuccess(response: PatchTimerCompleteResponse)

    fun onPatchTimerCompleteFailure(message: String)
}