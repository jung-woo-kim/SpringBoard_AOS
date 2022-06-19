package com.springboard.zzatmari.src.main.stat.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetCalendarResponse(
        @SerializedName("result")val result: GetCalendarResult
        ):BaseResponse()



