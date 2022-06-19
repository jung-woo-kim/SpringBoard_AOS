package com.springboard.zzatmari.src.initial_setting.time_pick

import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeResponse

interface InitialTimePickActivityView {
    fun onPostTimeSuccess(response: PostInitialTimeResponse)

    fun onPostTimeFailure(message: String)


}