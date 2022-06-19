package com.springboard.zzatmari.src.main.mypage


import com.springboard.zzatmari.src.main.mypage.account.models.GetUserInfoResponse
import com.springboard.zzatmari.src.main.mypage.models.GetPlantResponse
import com.springboard.zzatmari.src.main.mypage.store.models.GetSeedStoreDetailResponse
import com.springboard.zzatmari.src.main.mypage.store.models.GetSeedStoreResponse
import com.springboard.zzatmari.src.main.mypage.store.models.PostBuyingSeedRequest
import com.springboard.zzatmari.src.main.mypage.store.models.PostBuyingSeedResponse
import retrofit2.Call
import retrofit2.http.*

interface MyPageRetrofitInterface {
    @GET("/seeds")
    fun getSeedStore(): Call<GetSeedStoreResponse>
    @GET("/seeds/{seedIdx}")
    fun getSeedStoreDetail(@Path("seedIdx") seedIdx:Int): Call<GetSeedStoreDetailResponse>
    @POST("/seeds")
    fun postBuyingSeed(@Body params: PostBuyingSeedRequest):Call<PostBuyingSeedResponse>
    @GET("/plants")
    fun getPlant(): Call<GetPlantResponse>
    @GET("/users/{userIdx}")
    fun getUserInfo(@Path("userIdx") userIdx:Int): Call<GetUserInfoResponse>


}