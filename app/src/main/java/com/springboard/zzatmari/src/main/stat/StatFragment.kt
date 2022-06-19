package com.springboard.zzatmari.src.main.stat

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentTransaction
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentStatsBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.stat.models.GetCalendarResponse
import java.util.*

class StatFragment :BaseFragment<FragmentStatsBinding>(FragmentStatsBinding::bind,R.layout.fragment_stats),OnBackPressedListener,StatFragmentView{
    private var backKeyPressedTime=0L
    lateinit var service: StatService
    private var now_year=0
    private var now_month=0
    private var now_day=0
    private var now_tab="daily"
    var continuousDay=0
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service= StatService(this)

        val animation_text_init= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_right_init)
        val animation_layout_init= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_up_left_fix_init)
        binding.tabDailyStatsBtn.startAnimation(animation_layout_init)
        binding.tabDailyText.startAnimation(animation_text_init)
        val cal=Calendar.getInstance()
        now_year=cal.get(Calendar.YEAR)
        now_month=cal.get(Calendar.MONTH)
        now_day=cal.get(Calendar.DATE)
        service.tryGetCalendar(now_year,now_month+1)

        binding.statsMonthText.text=now_year.toString()+"년 "+(now_month+1).toString()+"월"
        binding.goNextBtn.setOnClickListener {
            now_month+=1
            binding.goPreBtn.alpha=1f
            binding.goPreBtn.isClickable=true
            if (now_month>11){
                now_year+=1
                now_month=0
            }
            val calendar=Calendar.getInstance()
            if (calendar.get(Calendar.MONTH)==now_month&&calendar.get(Calendar.YEAR)==now_year){
                binding.goNextBtn.alpha=0.6f
                binding.goNextBtn.isClickable=false
            }
            binding.statsMonthText.text=now_year.toString()+"년 "+(now_month+1).toString()+"월"
            binding.tabMonthlyStatsBtn.performClick()
            service.tryGetCalendar(now_year,now_month+1)
        }
        binding.goNextBtn.alpha=0.6f
        binding.goNextBtn.isClickable=false
        binding.goPreBtn.setOnClickListener {
            binding.goNextBtn.alpha=1f
            binding.goNextBtn.isClickable=true
            now_month-=1
            if (now_month<0){
                now_year-=1
                now_month=11
            }

            val calendar=Calendar.getInstance()
            if (calendar.get(Calendar.MONTH)==now_month&&calendar.get(Calendar.YEAR)-1==now_year){
                binding.goPreBtn.alpha=0.6f
                binding.goPreBtn.isClickable=false
            }
            binding.statsMonthText.text=now_year.toString()+"년 "+(now_month+1).toString()+"월"
            binding.tabMonthlyStatsBtn.performClick()
            service.tryGetCalendar(now_year,now_month+1)
        }
        now_tab="daily"

        binding.tabDailyStatsBtn.setOnClickListener {
            var animation_text= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_right)
            var animation_layout= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_up_left_fix)
            if(now_tab=="monthly"){
                animation_text= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_left_return)
                animation_layout= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_down_right_fix)
                binding.tabMonthlyStatsBtn.startAnimation(animation_layout)
                binding.tabMonthlyText.startAnimation(animation_text)
                animation_text= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_right)
                animation_layout= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_up_left_fix)
                binding.tabDailyStatsBtn.startAnimation(animation_layout)
                binding.tabDailyText.startAnimation(animation_text)
                binding.tabDailyStatsBtn.elevation=2F
                binding.tabMonthlyStatsBtn.elevation=1F
            }
            now_tab="daily"
            changeDailyFrag(now_year,now_month+1,now_day,now_tab,continuousDay)
        }
        binding.tabMonthlyStatsBtn.setOnClickListener {
            var animation_text= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_right)
            var animation_layout= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_up_left_fix)
            if(now_tab=="daily"){
                animation_text= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_right_return)
                animation_layout= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_down_left_fix)
                binding.tabDailyStatsBtn.startAnimation(animation_layout)
                binding.tabDailyText.startAnimation(animation_text)
                animation_text= AnimationUtils.loadAnimation(context,R.anim.tab_list_translate_left)
                animation_layout= AnimationUtils.loadAnimation(context,R.anim.tab_list_size_up_right_fix)
                binding.tabMonthlyStatsBtn.startAnimation(animation_layout)
                binding.tabMonthlyText.startAnimation(animation_text)
                binding.tabDailyStatsBtn.elevation=1F
                binding.tabMonthlyStatsBtn.elevation=2F
                now_day=1
            }
            now_tab="monthly"
            changeDailyFrag(now_year,now_month+1,now_day,now_tab,continuousDay)
        }

    }
    fun clickDaily(){
        binding.tabDailyStatsBtn.performClick()
    }

    fun changeToFrag(year: Int,month:Int,getCalendarResponse: GetCalendarResponse){
        childFragmentManager.beginTransaction()
                .replace(R.id.stats_calendar_frm, CalendarFragment(year,month,getCalendarResponse))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }
    fun changeDailyFrag(year: Int,month: Int,day:Int,type:String,continuousDay:Int){
        now_year=year
        now_month=month-1
        now_day=day
        childFragmentManager.beginTransaction()
            .replace(R.id.stats_daily_monthly_frm, StatKindsFragment(year,month,day,type,continuousDay))
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
    override fun onResume() {
        val a=activity as MainActivity
        a.setOnBackPressedListener(this)
        super.onResume()
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

    override fun onGetCalendarSuccess(response: GetCalendarResponse) {
        if (response.code==1000){
            continuousDay=response.result.continuousDay
            changeToFrag(now_year,now_month,response)
            changeDailyFrag(now_year,now_month+1,now_day,now_tab,response.result.continuousDay)
        }else{
            response.message?.let { showCustomToast(it) }
        }

    }

    override fun onGetCalendarFailure(message: String) {
        showCustomToast(message)
    }



}