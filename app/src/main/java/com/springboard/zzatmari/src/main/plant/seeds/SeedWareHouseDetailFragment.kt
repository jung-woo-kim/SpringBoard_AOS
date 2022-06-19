package com.springboard.zzatmari.src.main.plant.seeds

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentSeedWarehousePlantBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseDetailResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestRequest
import com.springboard.zzatmari.src.main.plant.seeds.models.PatchPlantOrHarvestResponse

class SeedWareHouseDetailFragment(val seedIdx:Int):BaseFragment<FragmentSeedWarehousePlantBinding>(FragmentSeedWarehousePlantBinding::bind, R.layout.fragment_seed_warehouse_plant),OnBackPressedListener,SeedWareHouseDetailFragmentView {

    val service=SeedWareHouseDetailService(this)
    var seedName=""
    var userSeedIdx=0
    private lateinit var removeDialog:Dialog
    private lateinit var secondDialog:Dialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service.tryGetSeedWareHouseDetail(seedIdx)
        removeDialog= Dialog(requireContext())
        removeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        removeDialog.setContentView(R.layout.dialog_seed_plant)


        secondDialog= Dialog(requireContext())
        secondDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        secondDialog.setContentView(R.layout.dialog_already_exist_seed)
        val paramsSecond=secondDialog.window?.attributes

        paramsSecond?.width= WindowManager.LayoutParams.MATCH_PARENT
        secondDialog.window?.attributes=paramsSecond
        secondDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val params=removeDialog.window?.attributes

        params?.width= WindowManager.LayoutParams.MATCH_PARENT
        removeDialog.window?.attributes=params
        removeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.plantPlantBtn.setOnClickListener {
            showDialog()
        }

    }
    override fun onResume() {
        val a=activity as MainActivity
        a.setOnBackPressedListener(this)
        super.onResume()
    }
    override fun onBackPressed() {
        parentFragmentManager.beginTransaction()
                .replace(R.id.plant_seed_warehouse_frm, SeedWareHouseSeedsFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }

    @SuppressLint("SetTextI18n")
    override fun onGetSeedWareHouseDetailSuccess(response: GetSeedWareHouseDetailResponse) {
        if (response.code==1000){
            userSeedIdx=response.result.seedIdx
            seedName=response.result.seedName
            binding.plantHasSeedText.text="보유수량 "+response.result.quantity.toString()+"개"
            binding.plantSeedNameText.text=response.result.seedName
            binding.plantSeedGrowUpText.text="누적 "+response.result.growthTime.toString()+"분 마다 성장"
            binding.plantSeedToFlowerText.text="개화시간 "+response.result.floweringTime.toString()+"분"
            binding.plantSeedRewardText.text="보상 "+response.result.reward.toString()+"햇살"
            binding.plantSeedPriceText.text=response.result.sunlight.toString()+"햇살"

            Glide.with(requireContext()).load(response.result.seedImgUrl).thumbnail(0.1f).into(binding.plantSeedImg)
        }else{
            response.message?.let { showCustomToast(it) }
        }


    }
    @SuppressLint("SetTextI18n")
    fun showDialog(){
        removeDialog.findViewById<TextView>(R.id.seed_plant_dialog_text_head).text=seedName+"을 심으시겠습니까?"
        removeDialog.show()
        removeDialog.findViewById<TextView>(R.id.seed_plant_dialog_no_btn).setOnClickListener {


            removeDialog.dismiss()
        }
        removeDialog.findViewById<TextView>(R.id.seed_plant_dialog_yes_btn).setOnClickListener {
            service.tryPatchPlantOrHarvest(PatchPlantOrHarvestRequest(seedIdx,0))


            removeDialog.dismiss()
        }
    }

    override fun onGetSeedWareHouseDetailFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPatchPlantOrHarvestSuccess(response: PatchPlantOrHarvestResponse) {
        if (response.code==1000){
            val a=activity as MainActivity
            a.changeFragtoPlant()
        }else if(response.code==2085){
            showSecondDialog()
        }else{
            response.message?.let { showCustomToast(it) }

        }

    }

    override fun onPatchPlantOrHarvestFailure(message: String) {
        showCustomToast(message)
    }
    fun showSecondDialog(){
        secondDialog.show()

        secondDialog.findViewById<TextView>(R.id.dialog_already_exist_close_btn).setOnClickListener {

            secondDialog.dismiss()
        }

    }
}