package com.springboard.zzatmari.src.main.list.timer.change

import android.os.Bundle
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityTimerChangeBinding
import com.springboard.zzatmari.src.main.list.timer.change.models.PatchTimeChangeRequest
import com.springboard.zzatmari.src.main.list.timer.change.models.PatchTimeChangeResponse

class TimerChangeActivity:BaseActivity<ActivityTimerChangeBinding>(ActivityTimerChangeBinding::inflate),TimerChangeActivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.timerChangeHourPicker.maxValue=23
        binding.timerChangeHourPicker.minValue=0
        binding.timerChangeMinutePicker.maxValue=59
        binding.timerChangeMinutePicker.minValue=0


        binding.timerChangeChangeBtn.setOnClickListener {
            val request=PatchTimeChangeRequest(binding.timerChangeHourPicker.value,binding.timerChangeMinutePicker.value)
            val service=TimerChangeService(this)
            val timerIdx=intent.getIntExtra("timerIdx",0)
            service.tryTimerListItem(timerIdx,request)


        }
        binding.timerChangeCancelBtn.setOnClickListener {
            finish()
        }
    }

    override fun onPatchTimerChangeSuccess(response: PatchTimeChangeResponse) {
        if (response.code==1000){
            showCustomToast("수정 완료!")
            finish()
        }else{
            response.message?.let { showCustomToast(it) }
        }
    }

    override fun onPatchTimerChangeFailure(message: String) {
        showCustomToast(message)
        finish()
    }
}