package com.springboard.zzatmari.src.initial_setting.time_pick.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PostInitialTimeResponse(
        @SerializedName("result")val result: PostInitialTimeResult
        ):BaseResponse()



