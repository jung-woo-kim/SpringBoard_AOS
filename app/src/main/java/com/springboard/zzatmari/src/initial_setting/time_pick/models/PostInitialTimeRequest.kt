package com.springboard.zzatmari.src.initial_setting.time_pick.models

import com.google.gson.annotations.SerializedName

data class PostInitialTimeRequest (
        @SerializedName("hour")val hour:Int,
        @SerializedName("minute")val minute:Int

        )

