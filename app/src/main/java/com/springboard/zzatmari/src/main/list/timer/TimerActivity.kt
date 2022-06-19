package com.springboard.zzatmari.src.main.list.timer

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityTimerBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.list.timer.models.*
import java.lang.Thread.sleep

class TimerActivity:BaseActivity<ActivityTimerBinding>(ActivityTimerBinding::inflate),TimerActivityView {
    private var time=""
    private var minute=0
    private var second=0
    private var handler = Handler(Looper.getMainLooper())
    private var condition=true
    private var service=TimerService(this)
    private var check=false
    private var executionIdx:String="0"
    private  var isBackPressed=false
    private  var isPaused=false
    private lateinit var removeDialog:Dialog
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeDialog= Dialog(this)
        removeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        removeDialog.setContentView(R.layout.dialog_end_timer)
        val params=removeDialog.window?.attributes

        params?.width= WindowManager.LayoutParams.MATCH_PARENT
        removeDialog.window?.attributes=params
        removeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val listIdx=intent.getIntExtra("listIdx", 0)
        val timerIdx=intent.getIntExtra("timerIdx", 0)

        var hour=0
        if (intent.getIntExtra("hour", 0)!=0){
            hour= intent.getIntExtra("hour", 0)
        }
        if (intent.getIntExtra("minute", 0)!=0){
            minute= intent.getIntExtra("minute", 0)
        }

        minute += hour * 60
        binding.minuteText.text=minute.toString()
        binding.secondText.text= "0$second"
        service.tryPostTimerStart(PostTimerStartRequest(listIdx, timerIdx))
        binding.timerStopBtn.setOnClickListener {
            binding.tiemrPauseLayout.visibility= View.GONE
            binding.tiemrStartLayout.visibility= View.VISIBLE
            service.tryPatchTimerPause(executionIdx, PatchTimerPauseRequest(minute, second))
            isPaused=true
        }
        binding.timerFinishBtn.setOnClickListener {
            binding.timerStopBtn.performClick()
            showDialog()
        }
        binding.timerStartBtn.setOnClickListener {
            isPaused=false
            binding.tiemrPauseLayout.visibility= View.VISIBLE
            binding.tiemrStartLayout.visibility= View.GONE
            service.tryPatchTimerContinue(executionIdx)
        }
        Thread(){
            while (condition){
                if (check){
                    handler.post {
                        if (second==-1){
                            second=59
                            minute-=1
                        }
                        if (second>9){
                            binding.minuteText.text=minute.toString()
                            binding.secondText.text=second.toString()
                        }else{
                            binding.minuteText.text=minute.toString()
                            binding.secondText.text= "0$second"
                        }

                        if(minute<=0 && second<=0){
                            condition=false
                            minute=0
                            second=0
                            binding.minuteText.text=minute.toString()
                            binding.secondText.text="0$second"
                            service.tryPatchTimerComplete(executionIdx,PatchTimerCompleteRequest(minute,second))
                        }
                        second-=1
                    }




                }
                sleep(1000)
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        condition=false
    }

    override fun onGetTimerNowSuccess(response: GetTimerNowResponse) {
        minute=response.result.min
        second=response.result.sec
        binding.minuteText.text=minute.toString()
        binding.secondText.text=second.toString()
    }

    override fun onGetTimerNowFailure(message: String) {
        showCustomToast("GetTimerNow에러")
    }

    override fun onPostTimerStartSuccess(response: PostTimerStartResponse) {
        executionIdx=response.result.executionIdx
        check=true
    }

    override fun onPostTimerStartFailure(message: String) {
        showCustomToast("PostTimerStart에러")
    }

    override fun onPatchTimerPauseSuccess(response: PatchTimerPauseResponse) {
        check=false
    }

    override fun onPatchTimerPauseFailure(message: String) {
        showCustomToast("PatchTimerPause에러")
    }

    override fun onPatchTimerContinueSuccess(response: PatchTimerContinueResponse) {
        service.tryGetTimerNow(executionIdx)
        check=true
    }

    override fun onPatchTimerContinueFailure(message: String) {
        showCustomToast("PatchTimerContinue에러")
    }

    override fun onPatchTimerCompleteSuccess(response: PatchTimerCompleteResponse) {
        check=false
        condition=false
        if (isBackPressed){
            finish()
        }else{
            val intent=Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }

    override fun onPatchTimerCompleteFailure(message: String) {
        showCustomToast("PatchTimerComplete에러")
    }

    override fun onBackPressed() {
        isBackPressed=true
        service.tryPatchTimerComplete(executionIdx, PatchTimerCompleteRequest(minute,second))
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        if (isPaused){
            check=false
        }

    }

    override fun onRestart() {
        super.onRestart()
        if (!isPaused){
            service.tryGetTimerNow(executionIdx)
            check=true
        }

    }
    fun showDialog(){
        removeDialog.show()
        removeDialog.findViewById<TextView>(R.id.dialog_end_timer_no_btn).setOnClickListener {
            removeDialog.dismiss()

        }
        removeDialog.findViewById<TextView>(R.id.dialog_end_timer_yes_btn).setOnClickListener {
            service.tryPatchTimerComplete(executionIdx, PatchTimerCompleteRequest(minute, second))
            removeDialog.dismiss()
        }
    }

}