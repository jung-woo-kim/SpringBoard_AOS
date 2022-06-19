package com.springboard.zzatmari.src.main.mypage.store

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.mypage.MyPageRetrofitInterface
import com.springboard.zzatmari.src.main.mypage.store.models.GetSeedStoreResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeedStoreService(val view: StoreFragmentView) {
    fun tryGetSeedStore(){
        val signRetrofitInterface= Application.sRetrofit.create(MyPageRetrofitInterface::class.java)
        signRetrofitInterface.getSeedStore().enqueue(object : Callback<GetSeedStoreResponse>{
            override fun onResponse(call: Call<GetSeedStoreResponse>, response: Response<GetSeedStoreResponse>) {
                view.onGetSeedStoreSuccess(response.body() as GetSeedStoreResponse)
            }

            override fun onFailure(call: Call<GetSeedStoreResponse>, t: Throwable) {
                view.onGetSeedStoreFailure(t.message?:"통신 오류")
            }

        })
    }

}