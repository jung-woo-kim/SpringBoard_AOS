package com.springboard.zzatmari.src.main.list.timer.add.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class PostTimeAddResponse(
        @SerializedName("result")val addResult: PostTimeAddResult
        ):BaseResponse()



