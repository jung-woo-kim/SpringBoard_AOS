package com.springboard.zzatmari.src.main.list.goal

import com.springboard.zzatmari.src.main.list.goal.models.GetGoalItemResponse
import com.springboard.zzatmari.src.main.list.goal.models.PostGoalRegisterResponse

interface ListGoalFragmentView {
    fun onGetGoalItemSuccess(response: GetGoalItemResponse)

    fun onGetGoalItemFailure(message: String)

    fun onPostGoalRegisterSuccess(response: PostGoalRegisterResponse)

    fun onPostGoalRegisterFailure(message: String)
}