package com.springboard.zzatmari.src.main.list.timer.add

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityTimerAddBinding
import com.springboard.zzatmari.src.main.list.timer.add.models.PostTimeAddRequest
import com.springboard.zzatmari.src.main.list.timer.add.models.PostTimeAddResponse

class TimerAddActivity:BaseActivity<ActivityTimerAddBinding>(ActivityTimerAddBinding::inflate), TimerAddActivityView {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.timerAddHourPicker.maxValue=23
        binding.timerAddHourPicker.minValue=0
        binding.timerAddMinutePicker.maxValue=59
        binding.timerAddMinutePicker.minValue=0


        binding.timerAddAddBtn.setOnClickListener {
            val service= TimerAddService(this)
            service.tryPostTimerItem(PostTimeAddRequest(binding.timerAddHourPicker.value,binding.timerAddMinutePicker.value))

        }
        binding.timerAddCancelBtn.setOnClickListener {
            finish()
        }
    }


    override fun onPostTimerSuccess(response: PostTimeAddResponse) {
        if(response.code==2032){
            response.message?.let { showCustomToast(it) }
        }else{
            finish()
            showCustomToast("추가 완료!")
        }

    }

    override fun onPostTimerFailure(message: String) {
        showCustomToast(message)
    }


}