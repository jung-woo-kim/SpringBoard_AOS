package com.springboard.zzatmari.src.splash

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.initial_setting.InitialRetrofitInterface
import com.springboard.zzatmari.src.initial_setting.models.GetAutoLoginResponse
import com.springboard.zzatmari.src.initial_setting.models.PostSignInRequest
import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashService(val view: SplashActivityView) {



    fun tryGetAutoLogin(){
        val signRetrofitInterface= Application.sRetrofit.create(InitialRetrofitInterface::class.java)
        signRetrofitInterface.getAutoLogin().enqueue(object : Callback<GetAutoLoginResponse>{
            override fun onResponse(call: Call<GetAutoLoginResponse>, response: Response<GetAutoLoginResponse>) {
                view.onGetAutoLoginSuccess(response.body() as GetAutoLoginResponse)
            }

            override fun onFailure(call: Call<GetAutoLoginResponse>, t: Throwable) {
                view.onGetAutoLoginFailure(t.message?:"통신 오류")
            }

        })
    }
}