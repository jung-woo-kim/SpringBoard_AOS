package com.springboard.zzatmari.src.account

import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse
import com.springboard.zzatmari.src.main.mypage.account.models.GetUserInfoResponse

interface LogInActivityView {

    fun onPostSignUpSuccess(response: PostSignInResponse)

    fun onPostSignUpFailure(message: String)

    fun onGetUserInfoSuccess(response: GetUserInfoResponse)

    fun onGetUserInfoFailure(message: String)



}