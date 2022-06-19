package com.springboard.zzatmari.src.main.list.models

import com.google.gson.annotations.SerializedName

data class PostListAddRequest (
        @SerializedName("listType")val listType:Int,
        @SerializedName("listItem")val listItem:String

        )

