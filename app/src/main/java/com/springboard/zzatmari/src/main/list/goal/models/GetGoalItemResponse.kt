package com.springboard.zzatmari.src.main.list.goal.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetGoalItemResponse(
        @SerializedName("result")val result: GetGoalItemResult
        ):BaseResponse()



