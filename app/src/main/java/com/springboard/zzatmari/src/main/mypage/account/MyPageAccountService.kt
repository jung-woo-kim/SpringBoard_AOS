package com.springboard.zzatmari.src.main.mypage.account

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.mypage.MyPageRetrofitInterface
import com.springboard.zzatmari.src.main.mypage.account.models.GetUserInfoResponse
import com.springboard.zzatmari.src.main.mypage.models.GetPlantResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageAccountService(val view: MyPageAccountView) {
    fun tryGetUserInfo(userIdx:Int){
        val signRetrofitInterface= Application.sRetrofit.create(MyPageRetrofitInterface::class.java)
        signRetrofitInterface.getUserInfo(userIdx).enqueue(object : Callback<GetUserInfoResponse>{
            override fun onResponse(call: Call<GetUserInfoResponse>, response: Response<GetUserInfoResponse>) {
                view.onGetUserInfoSuccess(response.body() as GetUserInfoResponse)
            }

            override fun onFailure(call: Call<GetUserInfoResponse>, t: Throwable) {
                view.onGetUserInfoFailure(t.message?:"통신 오류")
            }

        })
    }


}