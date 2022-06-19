package com.springboard.zzatmari.src.main.mypage.account.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetUserInfoResponse(
        @SerializedName("result")val result:GetUserInfoResult
        ):BaseResponse()



