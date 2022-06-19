package com.springboard.zzatmari.src.main.plant

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.bumptech.glide.Glide
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentPlantBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.plant.models.GetGrowingPlantResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseDetailResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestRequest
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestResponse

class PlantFragment:BaseFragment<FragmentPlantBinding>(FragmentPlantBinding::bind, R.layout.fragment_plant),OnBackPressedListener,PlantFragmentView {
    private var backKeyPressedTime=0L
    private var plantIdx=-1
    val service=PlantService(this)
    lateinit var animation_init1:Animation
    lateinit var plandDialog:Dialog
    var growTime=0
    var flowerTime=0
    var seedName=""
    var reward=0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plandDialog= Dialog(requireContext())
        plandDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        plandDialog.setContentView(R.layout.dialog_plant_info)
        val params=plandDialog.window?.attributes

        params?.width= WindowManager.LayoutParams.MATCH_PARENT
        plandDialog.window?.attributes=params
        plandDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.plantGage.progress=0
        animation_init1= AnimationUtils.loadAnimation(context,R.anim.plant_harvest)
        binding.plantSeedImg.setOnClickListener {
            showDialog()
        }
        binding.plantHarvestAnimation.addAnimatorListener(object :Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                binding.plantHarvestAnimation.visibility=View.GONE
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }

        })
        binding.plantHarvestAnimation.visibility=View.GONE

        binding.plantPlantNewSeedText.setOnClickListener {
            val a=activity as MainActivity
            a.changeFragtoSeedWareHouse()
        }
        service.tryGetGrowingPlant()



        animation_init1.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.plantPlantImg.visibility=View.GONE
                val a=activity as MainActivity
                a.changeFragtoPlant()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })


        binding.plantStoreBtn.setOnClickListener {

            val a=activity as MainActivity
            a.changeFragtoStore()
        }



        binding.plantSeedWarehouseBtn.setOnClickListener {
            val a=activity as MainActivity
            a.changeFragtoSeedWareHouse()
        }
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

    @SuppressLint("SetTextI18n")
    override fun onGetGrowingPlantSuccess(response: GetGrowingPlantResponse) {
        if(response.code==1000){
            service.tryGetSeedWareHouseDetail(response.result.seedIdx)
            plantIdx=response.result.plantIdx
            if(response.result.seedIdx==0){
                binding.plantPlantImg.visibility=View.GONE
                binding.plantSeedImg.visibility=View.GONE
                binding.plantPlantNewSeedText.visibility=View.VISIBLE
            }else{
                if (response.result.status==1){
                    binding.plantPlantImg.visibility=View.VISIBLE
                    binding.plantSeedImg.visibility=View.VISIBLE
                    binding.plantPlantNewSeedText.visibility=View.GONE
                    binding.plantTouchHarvestText.visibility=View.GONE
                    Glide.with(requireContext()).load(response.result.seedImgUrl).thumbnail(0.1f).into(binding.plantSeedImg)
                    Glide.with(requireContext()).load(response.result.plantImgUrl).thumbnail(0.1f).into(binding.plantPlantImg)
                    binding.plantGage.progress=(response.result.executionTime.toFloat()/response.result.floweringTime.toFloat()*100).toInt()
                    binding.plantGageText.text=response.result.executionTime.toString()+" min"
                }else if (response.result.status==0){
                    binding.plantPlantImg.visibility=View.VISIBLE
                    binding.plantSeedImg.visibility=View.VISIBLE
                    binding.plantPlantNewSeedText.visibility=View.GONE
                    binding.plantTouchHarvestText.visibility=View.GONE
                    Glide.with(requireContext()).load(response.result.seedImgUrl).thumbnail(0.1f).into(binding.plantSeedImg)
                    binding.plantGage.progress=(response.result.executionTime.toFloat()/response.result.floweringTime.toFloat()*100).toInt()
                    binding.plantGageText.text=response.result.executionTime.toString()+" min"
                }else if (response.result.status==2){//다자람
                    binding.plantPlantImg.visibility=View.VISIBLE
                    binding.plantSeedImg.visibility=View.GONE

                    binding.plantPlantNewSeedText.visibility=View.GONE
                    binding.plantTouchHarvestText.visibility=View.VISIBLE
                    binding.plantTouchHarvestText.setOnClickListener {
                        service.tryPatchPlantOrHarvest(PatchPlantOrHarvestRequest(plantIdx,1))

                    }
                    Glide.with(requireContext()).load(response.result.seedImgUrl).thumbnail(0.1f).into(binding.plantSeedImg)
                    Glide.with(requireContext()).load(response.result.plantImgUrl).thumbnail(0.1f).into(binding.plantPlantImg)
                    binding.plantGage.progress=(response.result.executionTime.toFloat()/response.result.floweringTime.toFloat()*100).toInt()
                    binding.plantGageText.text=response.result.executionTime.toString()+" hours"
                }
            }
        }



    }

    override fun onGetGrowingPlantFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPatchPlantOrHarvestSuccess(response: PatchPlantOrHarvestResponse) {
        if (response.code==1000){
            binding.plantHarvestAnimation.visibility=View.VISIBLE
            binding.plantHarvestAnimation.playAnimation()
            binding.plantPlantImg.startAnimation(animation_init1)
            binding.plantSeedImg.visibility=View.GONE
            binding.plantPlantNewSeedText.visibility=View.VISIBLE
            binding.plantTouchHarvestText.visibility=View.GONE

        }
    }

    override fun onPatchPlantOrHarvestFailure(message: String) {
        showCustomToast(message)
    }

    override fun onGetSeedWareHouseDetailSuccess(response: GetSeedWareHouseDetailResponse) {
        if (response.code==1000){
            flowerTime=response.result.floweringTime
            growTime=response.result.growthTime
            seedName=response.result.seedName
            reward=response.result.reward
        }

    }

    override fun onGetSeedWareHouseDetailFailure(message: String) {
        showCustomToast(message)
    }

    @SuppressLint("SetTextI18n")
    fun showDialog(){
        plandDialog.findViewById<TextView>(R.id.dialog_plant_info_seed_name_text).text=seedName
        plandDialog.findViewById<TextView>(R.id.dialog_plant_info_grow_time_text).text="누적시간 "+growTime.toString()+"분마다\n자라나는 씨앗입니다."
        plandDialog.findViewById<TextView>(R.id.dialog_plant_info_flower_time_text).text="모두 자라나는데\n"+flowerTime.toString()+"분이 걸립니다."
        plandDialog.findViewById<TextView>(R.id.dialog_plant_info_reward_text).text="꽃이 피면 "+reward.toString()+"햇살을\n드립니다."

        plandDialog.show()

        plandDialog.findViewById<TextView>(R.id.dialog_plant_info__btn).setOnClickListener {

            plandDialog.dismiss()
        }
    }


}