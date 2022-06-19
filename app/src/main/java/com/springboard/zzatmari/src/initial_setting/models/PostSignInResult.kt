package com.springboard.zzatmari.src.initial_setting.models

import com.google.gson.annotations.SerializedName

data class PostSignInResult(
        @SerializedName("jwt") val jwt:String,
        @SerializedName("userIdx") val userIdx:Int
)