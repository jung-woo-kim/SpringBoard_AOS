package com.springboard.zzatmari.src.account

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.initial_setting.InitialRetrofitInterface
import com.springboard.zzatmari.src.initial_setting.models.PostSignInRequest
import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse
import com.springboard.zzatmari.src.main.mypage.MyPageRetrofitInterface
import com.springboard.zzatmari.src.main.mypage.account.models.GetUserInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInService(val view: LogInActivityView) {


    fun tryPostSignUp(postSignInRequest: PostSignInRequest){
        val signRetrofitInterface= Application.sRetrofit.create(InitialRetrofitInterface::class.java)
        signRetrofitInterface.postSignIn(postSignInRequest,1).enqueue(object : Callback<PostSignInResponse>{
            override fun onResponse(call: Call<PostSignInResponse>, response: Response<PostSignInResponse>) {
                view.onPostSignUpSuccess(response.body() as PostSignInResponse)
            }

            override fun onFailure(call: Call<PostSignInResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message?:"통신 오류")
            }

        })
    }
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