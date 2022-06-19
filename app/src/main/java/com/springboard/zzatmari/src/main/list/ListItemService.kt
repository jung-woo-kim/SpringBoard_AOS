package com.springboard.zzatmari.src.main.list

import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.src.main.list.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListItemService(val view: ListItemActivityView) {
    fun tryGetListItem(type: Int){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.getListItem(type).enqueue(object : Callback<GetListItemResponse>{
            override fun onResponse(call: Call<GetListItemResponse>, response: Response<GetListItemResponse>) {
                view.onGetItemSuccess(response.body() as GetListItemResponse)
            }

            override fun onFailure(call: Call<GetListItemResponse>, t: Throwable) {
                view.onGetItemFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPostListAdd(postListAddRequest: PostListAddRequest){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.postListAdd(postListAddRequest).enqueue(object : Callback<PostListAddResponse>{
            override fun onResponse(call: Call<PostListAddResponse>, response: Response<PostListAddResponse>) {
                view.onPostListAddSuccess(response.body() as PostListAddResponse)
            }

            override fun onFailure(call: Call<PostListAddResponse>, t: Throwable) {
                view.onPostListAddFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPatchListChange(listIdx:Int,patchListChangeRequest: PatchListChangeRequest){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.patchListChange(listIdx,patchListChangeRequest).enqueue(object : Callback<PatchListChangeResponse>{
            override fun onResponse(call: Call<PatchListChangeResponse>, response: Response<PatchListChangeResponse>) {
                view.onPatchListChangeSuccess(response.body() as PatchListChangeResponse)
            }

            override fun onFailure(call: Call<PatchListChangeResponse>, t: Throwable) {
                view.onPatchListChangeFailure(t.message?:"통신 오류")
            }

        })
    }
    fun tryPatchListRemove(listIdx:Int){
        val signRetrofitInterface= Application.sRetrofit.create(ListRetrofitInterface::class.java)
        signRetrofitInterface.patchListRemove(listIdx).enqueue(object : Callback<PatchListRemoveResponse>{
            override fun onResponse(call: Call<PatchListRemoveResponse>, response: Response<PatchListRemoveResponse>) {
                view.onPatchListRemoveSuccess(response.body() as PatchListRemoveResponse)
            }

            override fun onFailure(call: Call<PatchListRemoveResponse>, t: Throwable) {
                view.onPatchListRemoveFailure(t.message?:"통신 오류")
            }

        })
    }
}