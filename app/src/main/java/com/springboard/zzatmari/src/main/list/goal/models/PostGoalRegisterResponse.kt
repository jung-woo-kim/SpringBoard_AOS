package com.springboard.zzatmari.src.main.list.goal.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PostGoalRegisterResponse(
        @SerializedName("result")val result: String
        ):BaseResponse()



