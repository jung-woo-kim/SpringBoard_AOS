package com.springboard.zzatmari.src.main.list

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.list.goal.models.GetGoalItemResponse
import com.springboard.zzatmari.src.main.list.models.PatchGoalResetRequest
import com.springboard.zzatmari.src.main.list.models.PatchGoalResetResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListService(val view: ListFragmentView) {
    fun tryGetListItem(){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.getGoalItem().enqueue(object : Callback<GetGoalItemResponse>{
            override fun onResponse(call: Call<GetGoalItemResponse>, response: Response<GetGoalItemResponse>) {
                view.onGetGoalItemSuccess(response.body() as GetGoalItemResponse)
            }

            override fun onFailure(call: Call<GetGoalItemResponse>, t: Throwable) {
                view.onGetGoalItemFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPatchGoalReset(patchGoalResetRequest: PatchGoalResetRequest){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.patchGoalReset(patchGoalResetRequest).enqueue(object : Callback<PatchGoalResetResponse>{
            override fun onResponse(call: Call<PatchGoalResetResponse>, response: Response<PatchGoalResetResponse>) {
                view.onPatchGoalResetSuccess(response.body() as PatchGoalResetResponse)
            }

            override fun onFailure(call: Call<PatchGoalResetResponse>, t: Throwable) {
                view.onPatchGoalResetFailure(t.message?:"통신 오류")
            }

        })
    }

}