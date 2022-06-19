package com.springboard.zzatmari.src.initial_setting.list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityInitialSelfDevelopmentBinding
import com.springboard.zzatmari.src.initial_setting.list.models.PostInitialListRequest
import com.springboard.zzatmari.src.initial_setting.list.models.PostInitialListResponse
import com.springboard.zzatmari.src.main.MainActivity

class InitialSelfDevelopmentActivity:BaseActivity<ActivityInitialSelfDevelopmentBinding>(ActivityInitialSelfDevelopmentBinding::inflate),InitialListActivityView {
    private var count=0
    private var handler = Handler(Looper.getMainLooper())
    var list=ArrayList<String>()
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding.initialSelfDevelopmentNextButton.setOnClickListener {
            val service=InitialListService(this)
            service.tryPostInitialTime(PostInitialListRequest(listItems = list,listType = 1))
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setClickOnText(binding.initialSelfDevelopmentSelectChinese)
        setClickOnText(binding.initialSelfDevelopmentSelectDaily)
        setClickOnText(binding.initialSelfDevelopmentSelectDevelopmentJapanese)
        setClickOnText(binding.initialSelfDevelopmentSelectEnglishSpeaking)
        setClickOnText(binding.initialSelfDevelopmentSelectFinance)
        setClickOnText(binding.initialSelfDevelopmentSelectGuitar)
        setClickOnText(binding.initialSelfDevelopmentSelectTextCertificate)
        setClickOnText(binding.initialSelfDevelopmentSelectTextEnglish)
        setClickOnText(binding.initialSelfDevelopmentSelectTextPlan)
        setClickOnText(binding.initialSelfDevelopmentSelectSpec)
        setClickOnText(binding.initialSelfDevelopmentSelectTextReadingNews)
        setClickOnText(binding.initialSelfDevelopmentSelectTextTed)
        setClickOnText(binding.initialSelfDevelopmentSelectWriting)
        Thread(){
            while (true){
                if(count<=0){
                    handler.post {
                        binding.initialSelfDevelopmentNextButton.isClickable=false
                        binding.initialSelfDevelopmentNextButton.background=applicationContext.resources.getDrawable(R.drawable.pink_opicity_view_radius_16)

                    }

                }else{
                    handler.post {
                        binding.initialSelfDevelopmentNextButton.isClickable=true
                        binding.initialSelfDevelopmentNextButton.background=applicationContext.resources.getDrawable(R.drawable.pink_view_radius_16)

                    }

                }
                Thread.sleep(100)
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
                view.background = applicationContext.resources.getDrawable(R.drawable.yellow_view_radius_17)
                view.setTextColor(resources.getColor(R.color.text_color))
                checkView=true
                count++
            }
            else{
                list.remove(view.text as String)
                view.background = applicationContext.resources.getDrawable(R.drawable.yellow_stroke_view_radius_17)
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