package com.springboard.zzatmari.src.main.list.timer.add.models

import com.google.gson.annotations.SerializedName

data class PostTimeAddRequest (
        @SerializedName("hour")val hour:Int,
        @SerializedName("minute")val minute:Int

        )

