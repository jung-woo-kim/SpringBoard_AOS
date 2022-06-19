package com.springboard.zzatmari.src.main.mypage.store

import com.springboard.zzatmari.src.main.mypage.store.models.GetSeedStoreResponse

interface StoreFragmentView {
    fun onGetSeedStoreSuccess(response: GetSeedStoreResponse)

    fun onGetSeedStoreFailure(message: String)

}