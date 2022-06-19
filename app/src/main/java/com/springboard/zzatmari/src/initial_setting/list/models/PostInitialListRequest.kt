package com.springboard.zzatmari.src.initial_setting.list.models

import com.google.gson.annotations.SerializedName

data class PostInitialListRequest (
        @SerializedName("listType")val listType:Int,
        @SerializedName("listItems")val listItems:ArrayList<String>

        )

