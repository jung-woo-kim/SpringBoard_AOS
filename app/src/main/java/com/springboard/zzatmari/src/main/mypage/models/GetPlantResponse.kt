package com.springboard.zzatmari.src.main.mypage.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetPlantResponse(
        @SerializedName("result")val result:ArrayList<GetPlantResult>
        ):BaseResponse()



