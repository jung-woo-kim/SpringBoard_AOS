package com.springboard.zzatmari.src.main.list.goal.models

import com.google.gson.annotations.SerializedName

data class PostGoalRegisterRequest (
        @SerializedName("listIdx")val listIdx:Int,
        @SerializedName("time")val time:Int
        )

