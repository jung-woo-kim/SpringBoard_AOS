package com.springboard.zzatmari.src.main.list.timer


import com.springboard.zzatmari.src.main.list.timer.models.*
import retrofit2.Call
import retrofit2.http.*

interface TimerRetrofitInterface {
    @POST("/execution")
    fun postTimerStart(@Body params:PostTimerStartRequest): Call<PostTimerStartResponse>
    @GET("/execution/{executionIdx}")
    fun getTimerNow(@Path("executionIdx") executionIdx:String):Call<GetTimerNowResponse>
    @PATCH("/execution/{executionIdx}/pause")
    fun patchTimerPause(@Path("executionIdx") executionIdx:String,@Body params: PatchTimerPauseRequest):Call<PatchTimerPauseResponse>
    @PATCH("/execution/{executionIdx}/continue")
    fun patchTimerContinue(@Path("executionIdx") executionIdx:String):Call<PatchTimerContinueResponse>
    @PATCH("/execution/{executionIdx}/complete")
    fun patchTimerComplete(@Path("executionIdx") executionIdx:String,@Body params: PatchTimerCompleteRequest):Call<PatchTimerCompleteResponse>

}