package com.springboard.zzatmari.src.account

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityLogonBinding
import com.springboard.zzatmari.src.initial_setting.models.PostSignInRequest
import com.springboard.zzatmari.src.initial_setting.models.PostSignInResponse
import com.springboard.zzatmari.src.initial_setting.time_pick.InitialTimePickActivity

class SignUpActivity:BaseActivity<ActivityLogonBinding>(ActivityLogonBinding::inflate),SignUpActivityView {
    lateinit var removeDialog: Dialog
    val service=SignUpService(this)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        removeDialog = Dialog(this)
        removeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        removeDialog.setContentView(R.layout.dialog_login_error)
        val params = removeDialog.window?.attributes

        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        removeDialog.window?.attributes = params
        removeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var email = ""
        var password = ""
        binding.logonBtn.setOnClickListener {
            service.tryPostSignUp(PostSignInRequest(email, password))
        }
        binding.emailEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                email = s.toString()
                if (email.length > 0 && password.length >= 6&&email.contains("@")) {
                    binding.logonBtn.isClickable = true
                    binding.logonBtn.alpha = 1F
                } else {
                    binding.logonBtn.isClickable = false
                    binding.logonBtn.alpha = 0.6F
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
                if (email.length > 0 && password.length >= 6&&email.contains("@")) {
                    binding.logonBtn.isClickable = true
                    binding.logonBtn.alpha = 1F
                } else {
                    binding.logonBtn.isClickable = false
                    binding.logonBtn.alpha = 0.6F
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    override fun onPostSignUpSuccess(response: PostSignInResponse) {
        if (response.code==1000){
            val edit= Application.sSharedPreferences.edit()
            edit.putInt("userIdx",response.result.userIdx)
            edit.putString(Application.X_ACCESS_TOKEN,response.result.jwt)
            edit.apply()
            showCustomToast("가입 완료!")
            finish()
        }else if(response.code==2015){
            showDialog(response.message!!)
        }else if(response.code==2016){
            showDialog(response.message!!)
        }else if(response.code==2017){
            showDialog(response.message!!)
        }else if(response.code==2018){
            showDialog(response.message!!)
        }else if(response.code==3011){
            showDialog(response.message!!)
        }else{
            showCustomToast(response.message!!)
        }
    }

    override fun onPostSignUpFailure(message: String) {
        showCustomToast(message)
    }
    fun showDialog(str:String){
        removeDialog.findViewById<TextView>(R.id.dialog_login_error_head_text).text=str
        removeDialog.show()
        removeDialog.findViewById<TextView>(R.id.dialog_login_error_btn).setOnClickListener {
            removeDialog.dismiss()

        }
    }
}