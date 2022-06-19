package com.springboard.zzatmari.src.main.mypage.account

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentMypageAccountBinding
import com.springboard.zzatmari.src.account.LogInActivity
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.mypage.account.models.GetUserInfoResponse

class MyPageAccountFragment:BaseFragment<FragmentMypageAccountBinding>(FragmentMypageAccountBinding::bind, R.layout.fragment_mypage_account),MyPageAccountView, OnBackPressedListener {
    val service=MyPageAccountService(this)
    private var backKeyPressedTime=0L
    lateinit var removeDialog:Dialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service.tryGetUserInfo(Application.sSharedPreferences.getInt("userIdx",0))
        binding.mypageAccountPlantHouseBtn.setOnClickListener {
            val a= activity as MainActivity
            a.changeFragtoMyPage()
        }
        removeDialog = Dialog(requireContext())
        removeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        removeDialog.setContentView(R.layout.dialog_log_out)
        val params = removeDialog.window?.attributes

        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        removeDialog.window?.attributes = params
        removeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.logOutButton.setOnClickListener {
            showDialog()
        }
        binding.dayTimeChangeLayout.setOnClickListener {
            val a=activity as MainActivity
            a.changeFragtoTimeChange()
        }
    }
    override fun onResume() {
        val a=activity as MainActivity
        a.setOnBackPressedListener(this)
        super.onResume()
    }

    override fun onGetUserInfoSuccess(response: GetUserInfoResponse) {
        binding.mypageAccountEmailText.text=response.result.email
    }

    override fun onGetUserInfoFailure(message: String) {
        showCustomToast(message)
    }

    override fun onBackPressed() {
        val a=activity as MainActivity
        if(System.currentTimeMillis() > backKeyPressedTime + 2000){
            backKeyPressedTime = System.currentTimeMillis();
            showCustomToast("한번 더 누를시 종료됩니다.")
            return
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
            a.finish()
        }
    }
    fun showDialog(){
        removeDialog.show()
        removeDialog.findViewById<TextView>(R.id.dialog_log_out_no_btn).setOnClickListener {
            removeDialog.dismiss()

        }
        removeDialog.findViewById<TextView>(R.id.dialog_log_out_yes_btn).setOnClickListener {
            val intent= Intent(requireContext(), LogInActivity::class.java)
            val edit=Application.sSharedPreferences.edit()
            edit.remove(Application.X_ACCESS_TOKEN)
            edit.remove("userIdx")
            edit.clear()
            edit.apply()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            removeDialog.dismiss()

        }
    }
}