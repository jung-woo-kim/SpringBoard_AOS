package com.springboard.zzatmari.src.main.list

import com.springboard.zzatmari.src.main.list.models.GetListItemResponse
import com.springboard.zzatmari.src.main.list.models.PatchListChangeResponse
import com.springboard.zzatmari.src.main.list.models.PatchListRemoveResponse
import com.springboard.zzatmari.src.main.list.models.PostListAddResponse

interface ListItemActivityView {
    fun onGetItemSuccess(response: GetListItemResponse)

    fun onGetItemFailure(message: String)

    fun onPostListAddSuccess(response: PostListAddResponse)

    fun onPostListAddFailure(message: String)

    fun onPatchListChangeSuccess(response: PatchListChangeResponse)

    fun onPatchListChangeFailure(message: String)

    fun onPatchListRemoveSuccess(response: PatchListRemoveResponse)

    fun onPatchListRemoveFailure(message: String)
}