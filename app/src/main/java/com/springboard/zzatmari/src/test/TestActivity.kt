package com.springboard.zzatmari.src.test

import android.os.Bundle
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityTestBinding

class TestActivity:BaseActivity<ActivityTestBinding>(ActivityTestBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showCustomToast("환영합니다")
    }
}