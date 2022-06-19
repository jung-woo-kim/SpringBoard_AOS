package com.springboard.zzatmari.src.initial_setting.list.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PostInitialListResponse(
        @SerializedName("result")val result: PostInitialListResult
        ):BaseResponse()



