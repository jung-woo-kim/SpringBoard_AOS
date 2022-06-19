package com.springboard.zzatmari.src.splash

import com.springboard.zzatmari.src.initial_setting.models.GetAutoLoginResponse
import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse

interface SplashActivityView {


    fun onGetAutoLoginSuccess(response: GetAutoLoginResponse)

    fun onGetAutoLoginFailure(message: String)
}