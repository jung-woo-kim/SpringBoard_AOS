package com.springboard.zzatmari.src.initial_setting

import com.springboard.zzatmari.src.initial_setting.list.models.PostInitialListRequest
import com.springboard.zzatmari.src.initial_setting.list.models.PostInitialListResponse
import com.springboard.zzatmari.src.initial_setting.models.GetAutoLoginResponse
import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeRequest
import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeResponse
import com.springboard.zzatmari.src.initial_setting.models.PostSignInRequest
import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.Query

interface InitialRetrofitInterface {
    @POST("/users/time")
    fun postInitialTime(@Body params: PostInitialTimeRequest):Call<PostInitialTimeResponse>

    @POST("/lists")
    fun postInitialList(@Body params: PostInitialListRequest):Call<PostInitialListResponse>

    @POST("/users")
    fun postSignIn(@Body params: PostSignInRequest, @Query("type") type:Int):Call<PostSignInResponse>

    @GET("/auto-login")
    fun getAutoLogin():Call<GetAutoLoginResponse>
    @POST("/sign-up")
    fun postSignUp(@Body params: PostSignInRequest):Call<PostSignInResponse>
}