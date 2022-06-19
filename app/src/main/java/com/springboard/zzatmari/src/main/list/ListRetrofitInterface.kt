package com.springboard.zzatmari.src.main.list


import com.springboard.zzatmari.src.main.list.goal.models.GetGoalItemResponse
import com.springboard.zzatmari.src.main.list.goal.models.PostGoalRegisterRequest
import com.springboard.zzatmari.src.main.list.goal.models.PostGoalRegisterResponse
import com.springboard.zzatmari.src.main.list.models.*
import com.springboard.zzatmari.src.main.list.timer.list.models.GetTimerListResponse
import com.springboard.zzatmari.src.main.list.timer.add.models.PostTimeAddRequest
import com.springboard.zzatmari.src.main.list.timer.add.models.PostTimeAddResponse
import com.springboard.zzatmari.src.main.list.timer.change.models.PatchTimeChangeRequest
import com.springboard.zzatmari.src.main.list.timer.change.models.PatchTimeChangeResponse
import com.springboard.zzatmari.src.main.list.timer.list.models.DeleteTimerListResponse
import retrofit2.Call
import retrofit2.http.*

interface ListRetrofitInterface {
    @GET("/lists")
    fun getListItem(@Query("type")type:Int): Call<GetListItemResponse>
    @POST("/lists")
    fun postListAdd(@Body params: PostListAddRequest):Call<PostListAddResponse>
    @GET("/goals")
    fun getGoalItem():Call<GetGoalItemResponse>
    @GET("/timers")
    fun getTimerList():Call<GetTimerListResponse>
    @POST("/timers")
    fun postTimer(@Body params: PostTimeAddRequest):Call<PostTimeAddResponse>
    @PATCH("/timers/{timerIdx}")
    fun patchTimer(@Path("timerIdx") timerIdx:Int, @Body params: PatchTimeChangeRequest):Call<PatchTimeChangeResponse>
    @DELETE("/timers/{timerIdx}")
    fun deleteTimer(@Path("timerIdx") timerIdx:Int):Call<DeleteTimerListResponse>
    @POST("/goals")
    fun postGoalRegister(@Body params: PostGoalRegisterRequest):Call<PostGoalRegisterResponse>
    @PATCH("/lists/{listIdx}")
    fun patchListChange(@Path("listIdx") listIdx:Int,@Body params: PatchListChangeRequest):Call<PatchListChangeResponse>
    @PATCH("/lists/{listIdx}/status")
    fun patchListRemove(@Path("listIdx") listIdx:Int):Call<PatchListRemoveResponse>
    @PATCH("/goals")
    fun patchGoalReset(@Body params: PatchGoalResetRequest):Call<PatchGoalResetResponse>
}