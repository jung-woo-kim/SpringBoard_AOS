package com.springboard.zzatmari.src.main.list.goal

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.list.ListRetrofitInterface
import com.springboard.zzatmari.src.main.list.goal.models.GetGoalItemResponse
import com.springboard.zzatmari.src.main.list.goal.models.PostGoalRegisterRequest
import com.springboard.zzatmari.src.main.list.goal.models.PostGoalRegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListGoalService(val view: ListGoalFragmentView) {
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
    fun tryPostGoalRegister(postGoalRegisterRequest: PostGoalRegisterRequest){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.postGoalRegister(postGoalRegisterRequest).enqueue(object : Callback<PostGoalRegisterResponse>{
            override fun onResponse(call: Call<PostGoalRegisterResponse>, response: Response<PostGoalRegisterResponse>) {
                view.onPostGoalRegisterSuccess(response.body() as PostGoalRegisterResponse)
            }

            override fun onFailure(call: Call<PostGoalRegisterResponse>, t: Throwable) {
                view.onPostGoalRegisterFailure(t.message?:"통신 오류")
            }

        })
    }
}