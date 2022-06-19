package com.springboard.zzatmari.src.initial_setting.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetAutoLoginResponse(
        @SerializedName("result")val result: GetAutoLoginResult
        ):BaseResponse()



