package com.springboard.zzatmari.src.main.list.timer.change.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PatchTimeChangeResponse(
        @SerializedName("result")val result: PatchTimeChangeResult
        ):BaseResponse()



