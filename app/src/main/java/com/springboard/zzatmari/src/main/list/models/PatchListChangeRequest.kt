package com.springboard.zzatmari.src.main.list.models

import com.google.gson.annotations.SerializedName

data class PatchListChangeRequest (
        @SerializedName("listItem")val listItem:String
        )

