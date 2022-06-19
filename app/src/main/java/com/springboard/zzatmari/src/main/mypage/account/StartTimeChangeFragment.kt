package com.springboard.zzatmari.src.main.mypage.account

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentStartTimeChangeBinding
import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeRequest
import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeResponse
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener

class StartTimeChangeFragment:BaseFragment<FragmentStartTimeChangeBinding>(FragmentStartTimeChangeBinding::bind, R.layout.fragment_start_time_change),StartTimeChangeActivityView,OnBackPressedListener {
    val service=StartTimeChangeService(this)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.settingTimerCompleteBtn.setOnClickListener {


            service.tryPostInitialTime(PostInitialTimeRequest(binding.startTimePicker.hour,binding.startTimePicker.minute))
        }
    }

    override fun onPostTimeSuccess(response: PostInitialTimeResponse) {
        if (response.code==1000){
            showCustomToast("설정 완료!")
            val a= activity as MainActivity
            a.changeFragtoMyPageAccount()
        }
    }
    override fun onResume() {
        val a=activity as MainActivity
        a.setOnBackPressedListener(this)
        super.onResume()
    }

    override fun onPostTimeFailure(message: String) {
        showCustomToast(message)
    }

    override fun onBackPressed() {
        val a=activity as MainActivity
        a.changeFragtoMyPageAccount()
    }
}