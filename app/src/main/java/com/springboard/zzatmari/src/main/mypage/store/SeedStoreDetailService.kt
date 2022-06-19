package com.springboard.zzatmari.src.main.mypage.store

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.mypage.MyPageRetrofitInterface
import com.springboard.zzatmari.src.main.mypage.store.models.GetSeedStoreDetailResponse
import com.springboard.zzatmari.src.main.mypage.store.models.PostBuyingSeedRequest
import com.springboard.zzatmari.src.main.mypage.store.models.PostBuyingSeedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeedStoreDetailService(val view: StoreDetailFragmentView) {
    fun tryGetSeedStoreDetail(seedIdx: Int){
        val signRetrofitInterface= Application.sRetrofit.create(MyPageRetrofitInterface::class.java)
        signRetrofitInterface.getSeedStoreDetail(seedIdx).enqueue(object : Callback<GetSeedStoreDetailResponse>{
            override fun onResponse(call: Call<GetSeedStoreDetailResponse>, response: Response<GetSeedStoreDetailResponse>) {
                view.onGetSeedStoreDetailSuccess(response.body() as GetSeedStoreDetailResponse)
            }

            override fun onFailure(call: Call<GetSeedStoreDetailResponse>, t: Throwable) {
                view.onGetSeedStoreDetailFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPostBuyingSeed(postBuyingSeedRequest: PostBuyingSeedRequest){
        val signRetrofitInterface= Application.sRetrofit.create(MyPageRetrofitInterface::class.java)
        signRetrofitInterface.postBuyingSeed(postBuyingSeedRequest).enqueue(object : Callback<PostBuyingSeedResponse>{
            override fun onResponse(call: Call<PostBuyingSeedResponse>, response: Response<PostBuyingSeedResponse>) {
                view.onPostBuyingSeedSuccess(response.body() as PostBuyingSeedResponse)
            }

            override fun onFailure(call: Call<PostBuyingSeedResponse>, t: Throwable) {
                view.onPostBuyingSeedFailure(t.message?:"통신 오류")
            }

        })
    }


}