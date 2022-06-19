package com.springboard.zzatmari.src.main.list.timer.list.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class DeleteTimerListResponse(
        @SerializedName("result")val result:DeleteTimerListResult
        ):BaseResponse()



