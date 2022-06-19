package com.springboard.zzatmari.src.initial_setting.time_pick

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.AcrivityInitialStartTimeBinding
import com.springboard.zzatmari.src.initial_setting.list.InitialDigitalDetoxActivity
import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeRequest
import com.springboard.zzatmari.src.initial_setting.time_pick.models.PostInitialTimeResponse

class InitialTimePickActivity:BaseActivity<AcrivityInitialStartTimeBinding>(AcrivityInitialStartTimeBinding::inflate), InitialTimePickActivityView {

    val service=InitialTimePickService(this)
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)









        binding.startTimeNextButton.setOnClickListener {
            val select_hour=binding.startTimePicker.hour
            val select_minute=binding.startTimePicker.minute

            val request= PostInitialTimeRequest(select_hour,select_minute)

            InitialTimePickService(this).tryPostInitialTime(request)
            startActivity(Intent(this, InitialDigitalDetoxActivity::class.java))
            finish()
        }



    }

    override fun onPostTimeSuccess(response: PostInitialTimeResponse) {
    }

    override fun onPostTimeFailure(message: String) {
        showCustomToast("에러")
    }




}