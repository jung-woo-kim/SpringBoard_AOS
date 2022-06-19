package com.springboard.zzatmari.src.main.list.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PatchGoalResetResponse(
        @SerializedName("result")val result:String
        ):BaseResponse()



