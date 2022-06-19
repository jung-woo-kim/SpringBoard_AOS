package com.springboard.zzatmari.src.main.mypage.store.models

import com.springboard.zzatmari.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetSeedStoreResponse(
        @SerializedName("result")val result: GetSeedStoreResult
        ):BaseResponse()



