package com.springboard.zzatmari.src.main.mypage.account


import com.springboard.zzatmari.src.main.mypage.account.models.GetUserInfoResponse


interface MyPageAccountView {
    fun onGetUserInfoSuccess(response: GetUserInfoResponse)

    fun onGetUserInfoFailure(message: String)



}