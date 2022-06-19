package com.springboard.zzatmari.src.account

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityLoginBinding
import com.springboard.zzatmari.src.initial_setting.models.PostSignInRequest
import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse
import com.springboard.zzatmari.src.initial_setting.time_pick.InitialTimePickActivity
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.mypage.account.models.GetUserInfoResponse

class LogInActivity:BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),LogInActivityView {
    lateinit var removeDialog: Dialog
    val service=LogInService(this)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        removeDialog= Dialog(this)
        removeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        removeDialog.setContentView(R.layout.dialog_login_error)
        val params=removeDialog.window?.attributes

        params?.width= WindowManager.LayoutParams.MATCH_PARENT
        removeDialog.window?.attributes=params
        removeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        
        var email=""
        var password=""
        binding.loginBtn.setOnClickListener {
            service.tryPostSignUp(PostSignInRequest(email, password))
        }
        binding.logonBtn.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.emailEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                email = s.toString()
                if (email.length > 0 && password.length >= 6 && email.contains("@")) {
                    binding.loginBtn.isClickable = true
                    binding.loginBtn.alpha = 1F
                } else {
                    binding.loginBtn.isClickable = false
                    binding.loginBtn.alpha = 0.6F
                }
            }

            override fun afterTextChanged(s: Editable?) {


            }

        })
        
        binding.passwordEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                password = s.toString()
                if (email.length > 0 && password.length >= 6 && email.contains("@")) {
                    binding.loginBtn.isClickable = true
                    binding.loginBtn.alpha = 1F
                } else {
                    binding.loginBtn.isClickable = false
                    binding.loginBtn.alpha = 0.6F
                }

            }

            override fun afterTextChanged(s: Editable?) {


            }

        })
        binding.forgetPasswordBtn.setOnClickListener {
            val email1 = Intent(Intent.ACTION_SEND)
            email1.type = "plain/text"
            val address = arrayOf("devspringboard@gmail.com")
            email1.putExtra(Intent.EXTRA_EMAIL, address)
            email1.putExtra(Intent.EXTRA_TEXT, "본인의 이메일을 적어서 보내주세요 :)")
            startActivity(email1)
        }
    }

    override fun onPostSignUpSuccess(response: PostSignInResponse) {
        if (response.code==1000){
            val edit= Application.sSharedPreferences.edit()
            edit.putInt("userIdx", response.result.userIdx)
            edit.putString(Application.X_ACCESS_TOKEN, response.result.jwt)
            edit.apply()
            service.tryGetUserInfo(response.result.userIdx)
        }else if(response.code==2015){
            showDialog(response.message!!)
        }else if(response.code==2016){
            showDialog(response.message!!)
        }else if(response.code==2017){
            showDialog(response.message!!)
        }else if(response.code==2018){
            showDialog(response.message!!)
        }else if(response.code==3012){
            showDialog(response.message!!)
        }else{
            showCustomToast(response.message!!)
        }

    }

    override fun onPostSignUpFailure(message: String) {
        showCustomToast(message)
    }

    override fun onGetUserInfoSuccess(response: GetUserInfoResponse) {
        if (response.code==1000){
            if (response.result.timeSet){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, InitialTimePickActivity::class.java))
                finish()
            }

        }
    }

    override fun onGetUserInfoFailure(message: String) {
        showCustomToast(message)
    }

    private var backKeyPressedTime=0L
    override fun onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            showCustomToast("한번 더 누를시 종료됩니다.")
            return
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            finish()
        }
    }
    fun showDialog(str: String){
        removeDialog.findViewById<TextView>(R.id.dialog_login_error_head_text).text=str
        removeDialog.show()
        removeDialog.findViewById<TextView>(R.id.dialog_login_error_btn).setOnClickListener {
            removeDialog.dismiss()

        }
    }
}