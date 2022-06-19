package com.springboard.zzatmari.src.main.list.timer

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.list.timer.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimerService(val view: TimerActivityView) {
    fun tryGetTimerNow(executionIdx:String){
        val signRetrofitInterface= Application.sRetrofit.create(TimerRetrofitInterface::class.java)
        signRetrofitInterface.getTimerNow(executionIdx).enqueue(object : Callback<GetTimerNowResponse>{
            override fun onResponse(call: Call<GetTimerNowResponse>, response: Response<GetTimerNowResponse>) {
                view.onGetTimerNowSuccess(response.body() as GetTimerNowResponse)
            }

            override fun onFailure(call: Call<GetTimerNowResponse>, t: Throwable) {
                view.onGetTimerNowFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPostTimerStart(postTimerStartRequest: PostTimerStartRequest){
        val signRetrofitInterface= Application.sRetrofit.create(TimerRetrofitInterface::class.java)
        signRetrofitInterface.postTimerStart(postTimerStartRequest).enqueue(object : Callback<PostTimerStartResponse>{
            override fun onResponse(call: Call<PostTimerStartResponse>, response: Response<PostTimerStartResponse>) {
                view.onPostTimerStartSuccess(response.body() as PostTimerStartResponse)
            }

            override fun onFailure(call: Call<PostTimerStartResponse>, t: Throwable) {
                view.onPostTimerStartFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPatchTimerPause(executionIdx:String,patchTimerPauseRequest: PatchTimerPauseRequest){
        val signRetrofitInterface= Application.sRetrofit.create(TimerRetrofitInterface::class.java)
        signRetrofitInterface.patchTimerPause(executionIdx,patchTimerPauseRequest).enqueue(object : Callback<PatchTimerPauseResponse>{
            override fun onResponse(call: Call<PatchTimerPauseResponse>, response: Response<PatchTimerPauseResponse>) {
                view.onPatchTimerPauseSuccess(response.body() as PatchTimerPauseResponse)
            }

            override fun onFailure(call: Call<PatchTimerPauseResponse>, t: Throwable) {
                view.onPatchTimerPauseFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPatchTimerContinue(executionIdx:String){
        val signRetrofitInterface= Application.sRetrofit.create(TimerRetrofitInterface::class.java)
        signRetrofitInterface.patchTimerContinue(executionIdx).enqueue(object : Callback<PatchTimerContinueResponse>{
            override fun onResponse(call: Call<PatchTimerContinueResponse>, response: Response<PatchTimerContinueResponse>) {
                view.onPatchTimerContinueSuccess(response.body() as PatchTimerContinueResponse)
            }

            override fun onFailure(call: Call<PatchTimerContinueResponse>, t: Throwable) {
                view.onPatchTimerContinueFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPatchTimerComplete(executionIdx:String,patchTimerCompleteRequest: PatchTimerCompleteRequest){
        val signRetrofitInterface= Application.sRetrofit.create(TimerRetrofitInterface::class.java)
        signRetrofitInterface.patchTimerComplete(executionIdx,patchTimerCompleteRequest).enqueue(object : Callback<PatchTimerCompleteResponse>{
            override fun onResponse(call: Call<PatchTimerCompleteResponse>, response: Response<PatchTimerCompleteResponse>) {
                view.onPatchTimerCompleteSuccess(response.body() as PatchTimerCompleteResponse)
            }

            override fun onFailure(call: Call<PatchTimerCompleteResponse>, t: Throwable) {
                view.onPatchTimerCompleteFailure(t.message?:"통신 오류")
            }

        })
    }

}