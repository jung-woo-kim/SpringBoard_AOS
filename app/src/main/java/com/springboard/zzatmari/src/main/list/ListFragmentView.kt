package com.springboard.zzatmari.src.main.list

import com.springboard.zzatmari.src.main.list.goal.models.GetGoalItemResponse
import com.springboard.zzatmari.src.main.list.models.*

interface ListFragmentView {
    fun onGetGoalItemSuccess(response: GetGoalItemResponse)

    fun onGetGoalItemFailure(message: String)

    fun onPatchGoalResetSuccess(response: PatchGoalResetResponse)

    fun onPatchGoalResetFailure(message: String)

}