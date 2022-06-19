package com.springboard.zzatmari.src.main.list.goal.models

import com.springboard.zzatmari.src.main.list.models.GetListItemResult
import com.google.gson.annotations.SerializedName

data class GetGoalItemResult(
        @SerializedName("resetCheck") val resetCheck:Boolean,
        @SerializedName("digitalDetox") val digitalDetox:ArrayList<GetListItemResult>,
        @SerializedName("selfDevelopment") val selfDevelopment:ArrayList<GetListItemResult>
)