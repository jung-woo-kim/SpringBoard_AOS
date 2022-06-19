package com.springboard.zzatmari.src.initial_setting.list

import com.springboard.zzatmari.src.initial_setting.list.models.PostInitialListResponse


interface InitialListActivityView {
    fun onPostListSuccess(response: PostInitialListResponse)

    fun onPostListFailure(message: String)
}