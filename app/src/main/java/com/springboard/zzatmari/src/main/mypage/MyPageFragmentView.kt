package com.springboard.zzatmari.src.main.mypage


import com.springboard.zzatmari.src.main.mypage.models.GetPlantResponse


interface MyPageFragmentView {
    fun onGetPlantSuccess(response: GetPlantResponse)

    fun onGetPlantFailure(message: String)



}