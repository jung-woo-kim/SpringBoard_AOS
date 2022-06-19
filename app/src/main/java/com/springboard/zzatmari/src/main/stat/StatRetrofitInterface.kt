package com.springboard.zzatmari.src.main.stat



import com.springboard.zzatmari.src.main.stat.models.GetCalendarListResponse
import com.springboard.zzatmari.src.main.stat.models.GetCalendarResponse
import retrofit2.Call
import retrofit2.http.*

interface StatRetrofitInterface {
    @GET("/stats")
    fun getCalendar(@Query("year")year:Int,@Query("month")month:Int): Call<GetCalendarResponse>
    @GET("/stats/{type}")
    fun getCalendarList(@Path("type")type: String, @Query("year")year:Int, @Query("month")month:Int, @Query("day")day:Int): Call<GetCalendarListResponse>
}