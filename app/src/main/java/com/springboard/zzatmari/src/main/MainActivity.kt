package com.springboard.zzatmari.src.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityMainBinding
import com.springboard.zzatmari.src.main.list.ListFragment
import com.springboard.zzatmari.src.main.mypage.MyPageFragment
import com.springboard.zzatmari.src.main.mypage.account.MyPageAccountFragment
import com.springboard.zzatmari.src.main.mypage.account.StartTimeChangeFragment
import com.springboard.zzatmari.src.main.mypage.store.StoreDetailFragment
import com.springboard.zzatmari.src.main.mypage.store.StoreFragment
import com.springboard.zzatmari.src.main.plant.PlantFragment
import com.springboard.zzatmari.src.main.plant.seeds.SeedWareHouseFragment
import com.springboard.zzatmari.src.main.stat.StatFragment

class MainActivity:BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    var listener: OnBackPressedListener?=null
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.menuMainBtmNavListIc.background=applicationContext.getDrawable(R.drawable.yellow_view_radious_100)
        binding.menuMainBtmNavStatIc.background=applicationContext.getDrawable(R.color.white)
        binding.menuMainBtmNavPlantIc.background=applicationContext.getDrawable(R.color.white)
        binding.menuMainBtmNavMypageIc.background=applicationContext.getDrawable(R.color.white)
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ListFragment())
                .commitAllowingStateLoss()
        binding.menuMainBtmNavList.setOnClickListener {
            binding.menuMainBtmNavListIc.background=applicationContext.getDrawable(R.drawable.yellow_view_radious_100)
            binding.menuMainBtmNavStatIc.background=applicationContext.getDrawable(R.color.white)
            binding.menuMainBtmNavPlantIc.background=applicationContext.getDrawable(R.color.white)
            binding.menuMainBtmNavMypageIc.background=applicationContext.getDrawable(R.color.white)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, ListFragment())
                    .commitAllowingStateLoss()
//
//            binding.root.setBackgroundResource(R.color.background)
        }
        binding.mainBtmNavStat.setOnClickListener {
            binding.menuMainBtmNavListIc.background=applicationContext.getDrawable(R.color.white)
            binding.menuMainBtmNavStatIc.background=applicationContext.getDrawable(R.drawable.yellow_view_radious_100)
            binding.menuMainBtmNavPlantIc.background=applicationContext.getDrawable(R.color.white)
            binding.menuMainBtmNavMypageIc.background=applicationContext.getDrawable(R.color.white)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, StatFragment())
                    .commitAllowingStateLoss()
//            binding.root.setBackgroundResource(R.color.background)
        }
        binding.menuMainBtmNavPlant.setOnClickListener {
            binding.menuMainBtmNavListIc.background=applicationContext.getDrawable(R.color.white)
            binding.menuMainBtmNavStatIc.background=applicationContext.getDrawable(R.color.white)
            binding.menuMainBtmNavPlantIc.background=applicationContext.getDrawable(R.drawable.yellow_view_radious_100)
            binding.menuMainBtmNavMypageIc.background=applicationContext.getDrawable(R.color.white)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, PlantFragment())
                    .commitAllowingStateLoss()


//            binding.root.setBackgroundResource(R.color.ground)
        }
        binding.menuMainBtmNavMypage.setOnClickListener {
            binding.menuMainBtmNavListIc.background=applicationContext.getDrawable(R.color.white)
            binding.menuMainBtmNavStatIc.background=applicationContext.getDrawable(R.color.white)
            binding.menuMainBtmNavPlantIc.background=applicationContext.getDrawable(R.color.white)
            binding.menuMainBtmNavMypageIc.background=applicationContext.getDrawable(R.drawable.yellow_view_radious_100)
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MyPageAccountFragment())
                .commitAllowingStateLoss()

//            binding.root.setBackgroundResource(R.color.background)
        }



    }
    var check=false
    fun getGesture():Boolean{
        return check
    }
    fun setGesture(){
        check=false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val action: Int = MotionEventCompat.getActionMasked(event)

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                check=true
                true
            }
            MotionEvent.ACTION_MOVE -> {
                check=true
                true
            }
            MotionEvent.ACTION_UP -> {
                check=true
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                check=true
                true
            }
            MotionEvent.ACTION_OUTSIDE -> {
                check=true
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
    fun setOnBackPressedListener(listener: OnBackPressedListener){
        this.listener=listener
    }

    override fun onBackPressed() {
        if (listener!=null){
            listener?.onBackPressed()
        }else{
            super.onBackPressed()
        }

    }
    fun changeFragtoSeedWareHouse(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, SeedWareHouseFragment())
                .commitAllowingStateLoss()
    }
    fun changeFragtoPlant(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, PlantFragment())
                .commitAllowingStateLoss()
    }
    fun changeFragtoStore(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, StoreFragment())
            .commitAllowingStateLoss()
    }
    fun changeFragtoStoreDetail(seedIdx:Int){
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, StoreDetailFragment(seedIdx))
                .commitAllowingStateLoss()
    }
    fun changeFragtoMyPageAccount(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MyPageAccountFragment())
                .commitAllowingStateLoss()
    }
    fun changeFragtoMyPage(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MyPageFragment())
                .commitAllowingStateLoss()
    }
    fun changeFragtoTimeChange(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, StartTimeChangeFragment())
                .commitAllowingStateLoss()
    }
}