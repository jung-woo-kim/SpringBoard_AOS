package com.springboard.zzatmari.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivitySplashBinding
import com.springboard.zzatmari.src.account.LogInActivity
import com.springboard.zzatmari.src.initial_setting.models.GetAutoLoginResponse
import com.springboard.zzatmari.src.initial_setting.time_pick.InitialTimePickActivity
import com.springboard.zzatmari.src.initial_setting.models.PostSignInRequest
import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse
import com.springboard.zzatmari.src.main.MainActivity
import java.util.*

class SplashActivity :BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate),SplashActivityView{
    val service=SplashService(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Application.sSharedPreferences.getString(Application.X_ACCESS_TOKEN, null)==null){
            Handler(Looper.getMainLooper()).postDelayed({

                startActivity(Intent(this, LogInActivity::class.java))
                finish()
            }, 1500)
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                service.tryGetAutoLogin()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 1500)
        }

    }


    override fun onGetAutoLoginSuccess(response: GetAutoLoginResponse) {

        if (response.code!=1000){
            showCustomToast(response.message.toString())
        }
    }

    override fun onGetAutoLoginFailure(message: String) {
        showCustomToast(message)
    }
}