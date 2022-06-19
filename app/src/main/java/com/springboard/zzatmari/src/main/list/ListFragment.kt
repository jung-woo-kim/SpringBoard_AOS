package com.springboard.zzatmari.src.main.list

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentListBinding
import com.springboard.zzatmari.src.main.list.digital_detox.ListDigitalDetoxFragment
import com.springboard.zzatmari.src.main.list.goal.ListGoalFragment
import com.springboard.zzatmari.src.main.list.goal.models.GetGoalItemResponse
import com.springboard.zzatmari.src.main.list.models.PatchGoalResetRequest
import com.springboard.zzatmari.src.main.list.models.PatchGoalResetResponse
import com.springboard.zzatmari.src.main.list.self_development.ListSelfDevelopmentFragment

class ListFragment:BaseFragment<FragmentListBinding>(FragmentListBinding::bind, R.layout.fragment_list),ListFragmentView {
    private var nowPage=""
    private lateinit var firstDialog:Dialog
    private var service=ListService(this)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service.tryGetListItem()
        changeToDigitalDetoxFrag()
        nowPage="digital"
        val animation_init1= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_right_init)
        binding.listDigitalDetoxText.startAnimation(animation_init1)
        val animation_init= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_up_left_fix_init)
        binding.listDigitalDetoxBtn.startAnimation(animation_init)
        firstDialog= Dialog(requireContext())
        firstDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        firstDialog.setContentView(R.layout.dialog_goal_question)
        val params=firstDialog.window?.attributes

        params?.width= WindowManager.LayoutParams.MATCH_PARENT
        firstDialog.window?.attributes=params
        firstDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.listDigitalDetoxBtn.setOnClickListener {

            if (nowPage=="develop"){
                val animation= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_down_center_fix)
                binding.listSelfDevelopmentBtn.startAnimation(animation)
            }
            if(nowPage=="goal"){
                val animation= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_down_right_fix)
                binding.listGoalBtn.startAnimation(animation)
                val animation1= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_left_return)
                binding.listGoalText.startAnimation(animation1)
            }
            if (nowPage!="digital"){
                changeToDigitalDetoxFrag()
                binding.listDigitalDetoxBtn.elevation=3F
                binding.listSelfDevelopmentBtn.elevation= 2F
                binding.listGoalBtn.elevation=1F
                nowPage="digital"
            }
            val animation1= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_right)
            binding.listDigitalDetoxText.startAnimation(animation1)
            val animation= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_up_left_fix)
            binding.listDigitalDetoxBtn.startAnimation(animation)
        }


        binding.listSelfDevelopmentBtn.setOnClickListener {

            if (nowPage=="digital"){
                val animation= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_down_left_fix)
                binding.listDigitalDetoxBtn.startAnimation(animation)
                val animation1= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_right_return)
                binding.listDigitalDetoxText.startAnimation(animation1)
            }
            if(nowPage=="goal"){
                val animation= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_down_right_fix)
                binding.listGoalBtn.startAnimation(animation)
                val animation1= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_left_return)
                binding.listGoalText.startAnimation(animation1)
            }
            if (nowPage!="develop"){
                changeToSelfDevelopmentFrag()
                binding.listSelfDevelopmentBtn.elevation= 3F
                binding.listDigitalDetoxBtn.elevation=1F
                binding.listGoalBtn.elevation=2F
                nowPage="develop"
            }
            val animation= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_up_center_fix)
            binding.listSelfDevelopmentBtn.startAnimation(animation)
        }
        binding.listGoalBtn.setOnClickListener {
            if (nowPage=="digital"){
                val animation= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_down_center_fix)
                binding.listDigitalDetoxBtn.startAnimation(animation)
                val animation1= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_right_return)
                binding.listDigitalDetoxText.startAnimation(animation1)
            }
            if (nowPage=="develop"){
                val animation= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_down_left_fix)
                binding.listSelfDevelopmentBtn.startAnimation(animation)
            }
            if(nowPage!="goal"){
                changeToGoalFrag()
                binding.listSelfDevelopmentBtn.elevation= 1F
                binding.listDigitalDetoxBtn.elevation=2F
                binding.listGoalBtn.elevation=3F
                nowPage="goal"
            }
            val animation1= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_left)
            binding.listGoalText.startAnimation(animation1)
            val animation= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_up_right_fix)
            binding.listGoalBtn.startAnimation(animation)
        }


    }
    fun changeToDigitalDetoxFrag(){
        childFragmentManager.beginTransaction()
                .replace(R.id.list_frm, ListDigitalDetoxFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }
    fun changeToSelfDevelopmentFrag(){
        childFragmentManager.beginTransaction()
                .replace(R.id.list_frm, ListSelfDevelopmentFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }
    fun changeToGoalFrag(){
        childFragmentManager.beginTransaction()
                .replace(R.id.list_frm, ListGoalFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }
    @SuppressLint("SetTextI18n")
    fun showDialog(){
        firstDialog.show()
        firstDialog.findViewById<TextView>(R.id.dialog_goal_question_no_btn).setOnClickListener {
            service.tryPatchGoalReset(PatchGoalResetRequest(0))
            firstDialog.dismiss()
        }
        firstDialog.findViewById<TextView>(R.id.dialog_goal_question_yes_btn).setOnClickListener {
            service.tryPatchGoalReset(PatchGoalResetRequest(1))
            binding.listGoalBtn.performClick()
            firstDialog.dismiss()
        }
    }

    override fun onGetGoalItemSuccess(response: GetGoalItemResponse) {


    }

    override fun onGetGoalItemFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPatchGoalResetSuccess(response: PatchGoalResetResponse) {
        if (response.code!=1000){
            showCustomToast(response.message.toString())
        }
    }

    override fun onPatchGoalResetFailure(message: String) {
        showCustomToast(message)
    }


}