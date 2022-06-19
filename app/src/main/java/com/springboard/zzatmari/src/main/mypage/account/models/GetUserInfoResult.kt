package com.springboard.zzatmari.src.main.mypage.account.models

import com.google.gson.annotations.SerializedName

data class GetUserInfoResult(
        @SerializedName("email") val email:String,
        @SerializedName("timeSet") val timeSet:Boolean
)