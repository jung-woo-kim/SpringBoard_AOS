package com.springboard.zzatmari.src.account

import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse

interface SignUpActivityView {

    fun onPostSignUpSuccess(response: PostSignInResponse)

    fun onPostSignUpFailure(message: String)


}