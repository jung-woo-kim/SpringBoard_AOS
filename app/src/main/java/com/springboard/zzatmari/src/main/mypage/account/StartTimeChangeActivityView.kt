package com.springboard.zzatmari.src.main.mypage.account

import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeResponse

interface StartTimeChangeActivityView {
    fun onPostTimeSuccess(response: PostInitialTimeResponse)

    fun onPostTimeFailure(message: String)


}