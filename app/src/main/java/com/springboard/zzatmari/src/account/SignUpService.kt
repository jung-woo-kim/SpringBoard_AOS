package com.springboard.zzatmari.src.account

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.initial_setting.InitialRetrofitInterface
import com.springboard.zzatmari.src.initial_setting.models.PostSignInRequest
import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService(val view: SignUpActivityView) {


    fun tryPostSignUp(postSignInRequest: PostSignInRequest){
        val signRetrofitInterface= Application.sRetrofit.create(InitialRetrofitInterface::class.java)
        signRetrofitInterface.postSignUp(postSignInRequest).enqueue(object : Callback<PostSignInResponse>{
            override fun onResponse(call: Call<PostSignInResponse>, response: Response<PostSignInResponse>) {
                view.onPostSignUpSuccess(response.body() as PostSignInResponse)
            }

            override fun onFailure(call: Call<PostSignInResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message?:"통신 오류")
            }

        })
    }

}