package com.springboard.zzatmari.src.main.mypage.store

import com.springboard.zzatmari.src.main.mypage.store.models.GetSeedStoreDetailResponse
import com.springboard.zzatmari.src.main.mypage.store.models.PostBuyingSeedResponse

interface StoreDetailFragmentView {
    fun onGetSeedStoreDetailSuccess(response: GetSeedStoreDetailResponse)

    fun onGetSeedStoreDetailFailure(message: String)

    fun onPostBuyingSeedSuccess(response: PostBuyingSeedResponse)

    fun onPostBuyingSeedFailure(message: String)

}