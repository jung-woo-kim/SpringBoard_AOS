package com.springboard.zzatmari.src.initial_setting.list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityInitialDigitalDetoxBinding
import com.springboard.zzatmari.src.initial_setting.list.models.PostInitialListRequest
import com.springboard.zzatmari.src.initial_setting.list.models.PostInitialListResponse
import java.lang.Thread.sleep

class InitialDigitalDetoxActivity: BaseActivity<ActivityInitialDigitalDetoxBinding>(ActivityInitialDigitalDetoxBinding::inflate),InitialListActivityView {
    private var count=0
    private var handler = Handler(Looper.getMainLooper())
    private var list=ArrayList<String>()
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.initialDigitalDetoxNextButton.setOnClickListener {
            val service=InitialListService(this)
            service.tryPostInitialTime(PostInitialListRequest(listItems = list,listType = 0))
            startActivity(Intent(this, InitialSelfDevelopmentActivity::class.java))
            finish()
        }

        setClickOnText(binding.initialDigitalDetoxSelectTextBruise)
        setClickOnText(binding.initialDigitalDetoxSelectTextClean)
        setClickOnText(binding.initialDigitalDetoxSelectTextExercise)
        setClickOnText(binding.initialDigitalDetoxSelectTextGoOut)
        setClickOnText(binding.initialDigitalDetoxSelectTextMeditation)
        setClickOnText(binding.initialDigitalDetoxSelectTextMemo)
        setClickOnText(binding.initialDigitalDetoxSelectTextReading)
        setClickOnText(binding.initialDigitalDetoxSelectTextSleep)
        setClickOnText(binding.initialDigitalDetoxSelectTextStretch)
        setClickOnText(binding.initialDigitalDetoxSelectTextThinking)
        setClickOnText(binding.initialDigitalDetoxSelectTextWalk)
        setClickOnText(binding.initialDigitalDetoxSelectTextAccount)
        setClickOnText(binding.initialDigitalDetoxSelectTextDrawing)

        Thread(){
            while (true){
                if(count<=0){
                    handler.post {
                        binding.initialDigitalDetoxNextButton.isClickable=false
                        binding.initialDigitalDetoxNextButton.background=applicationContext.getDrawable(R.drawable.pink_opicity_view_radius_16)

                    }

                }else{
                    handler.post {
                        binding.initialDigitalDetoxNextButton.isClickable=true
                        binding.initialDigitalDetoxNextButton.background=applicationContext.getDrawable(R.drawable.pink_view_radius_16)

                    }

                }
                sleep(100)
            }

        }.start()


    }
    @SuppressLint("UseCompatLoadingForDrawables")
    fun setClickOnText(view:TextView){
        var checkView=false
        view.isClickable=true
        view.setOnClickListener {
            if (!checkView){
                list.add(view.text as String)
                view.background = applicationContext.getDrawable(R.drawable.yellow_view_radius_17)
                view.setTextColor(resources.getColor(R.color.text_color))
                checkView=true
                count++
            }
            else{
                list.remove(view.text as String)
                view.background = applicationContext.getDrawable(R.drawable.yellow_stroke_view_radius_17)
                view.setTextColor(resources.getColor(R.color.text_color_nonactive))
                checkView=false
                count--
            }
        }
    }

    override fun onPostListSuccess(response: PostInitialListResponse) {
        if (response.code!=1000){
            showCustomToast(response.message.toString())
        }
    }

    override fun onPostListFailure(message: String) {
        showCustomToast(message)
    }
}