package com.springboard.zzatmari.src.main.list.timer.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PatchTimerCompleteResponse(
        @SerializedName("result")val result:String
        ):BaseResponse()



