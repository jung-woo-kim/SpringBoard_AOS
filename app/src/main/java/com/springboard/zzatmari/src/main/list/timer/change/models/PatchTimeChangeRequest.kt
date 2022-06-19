package com.springboard.zzatmari.src.main.list.timer.change.models

import com.google.gson.annotations.SerializedName

data class PatchTimeChangeRequest (
        @SerializedName("hour")val hour:Int,
        @SerializedName("minute")val minute:Int

        )

