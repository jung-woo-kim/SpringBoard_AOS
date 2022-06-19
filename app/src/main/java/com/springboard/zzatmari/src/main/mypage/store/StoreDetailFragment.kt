package com.springboard.zzatmari.src.main.mypage.store

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.bumptech.glide.Glide
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentStoreDetailBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.mypage.store.models.GetSeedStoreDetailResponse
import com.springboard.zzatmari.src.main.mypage.store.models.PostBuyingSeedRequest
import com.springboard.zzatmari.src.main.mypage.store.models.PostBuyingSeedResponse

class StoreDetailFragment(val seedIdx:Int):BaseFragment<FragmentStoreDetailBinding>(FragmentStoreDetailBinding::bind, R.layout.fragment_store_detail),StoreDetailFragmentView,OnBackPressedListener {
    lateinit var service: SeedStoreDetailService
    private lateinit var firstDialog:Dialog
    private lateinit var secondDialog:Dialog
    private lateinit var seedName:String
    private var seedPrice=0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstDialog= Dialog(requireContext())
        firstDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        firstDialog.setContentView(R.layout.dialog_buying_question)
        val params=firstDialog.window?.attributes

        params?.width= WindowManager.LayoutParams.MATCH_PARENT
        firstDialog.window?.attributes=params
        firstDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        secondDialog= Dialog(requireContext())
        secondDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        secondDialog.setContentView(R.layout.dialog_buying_question_second)
        val paramsSecond=secondDialog.window?.attributes

        paramsSecond?.width= WindowManager.LayoutParams.MATCH_PARENT
        secondDialog.window?.attributes=paramsSecond
        secondDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        service= SeedStoreDetailService(this)
        service.tryGetSeedStoreDetail(seedIdx)
        binding.plantPlantBtn.setOnClickListener {
            showDialog()
        }
        
    }
    override fun onResume() {
        val a=activity as MainActivity
        a.setOnBackPressedListener(this)
        super.onResume()
    }

    @SuppressLint("SetTextI18n")
    override fun onGetSeedStoreDetailSuccess(response: GetSeedStoreDetailResponse) {
        if (response.code==1000){
            seedName=response.result.seedName
            seedPrice=response.result.sunlight
            binding.mypageShopSunnyNum.text=response.result.mySunlight.toString()+"햇살"
            binding.plantHasSeedText.text="보유수량 "+response.result.quantity.toString()+"개"
            binding.plantSeedNameText.text=response.result.seedName
            binding.plantSeedGrowUpText.text="누적 "+response.result.growthTime.toString()+"분 마다 성장"
            binding.plantSeedToFlowerText.text="개화시간 "+response.result.floweringTime.toString()+"분"
            binding.plantSeedRewardText.text="보상 "+response.result.reward.toString()+"햇살"
            binding.plantSeedPriceText.text=response.result.sunlight.toString()+"햇살"
//            binding.mypageShopSunnyNum.text=response.result.mySunlight.toString()+"햇살"
            Glide.with(requireContext()).load(response.result.seedImgUrl).thumbnail(0.1f).into(binding.plantSeedImg)
        }else{
            response.message?.let { showCustomToast(it) }
        }
    }

    override fun onGetSeedStoreDetailFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPostBuyingSeedSuccess(response: PostBuyingSeedResponse) {
        if (response.code==1000){
            showSecondDialog()
        }else{
            response.message?.let { showCustomToast(it) }
        }

    }

    override fun onPostBuyingSeedFailure(message: String) {
        showCustomToast(message)
    }
    @SuppressLint("SetTextI18n")
    fun showDialog(){
        firstDialog.show()
        firstDialog.findViewById<TextView>(R.id.dialog_buying_question_head_text).text=seedName+"을 구매하시겠습니까?"
        firstDialog.findViewById<TextView>(R.id.dialog_buying_question_head_text2).text=seedPrice.toString()+"햇살로 구매되어집니다."
        firstDialog.findViewById<TextView>(R.id.dialog_buying_question_no_btn).setOnClickListener {
            firstDialog.dismiss()
        }
        firstDialog.findViewById<TextView>(R.id.dialog_buying_question_yes_btn).setOnClickListener {
            service.tryPostBuyingSeed(PostBuyingSeedRequest(seedIdx))
            firstDialog.dismiss()
        }
    }
    @SuppressLint("SetTextI18n")
    fun showSecondDialog(){
        secondDialog.show()
        secondDialog.findViewById<TextView>(R.id.dialog_buying_question_second_head_text).text=seedName+"이 구매되었습니다!"
        secondDialog.findViewById<TextView>(R.id.dialog_buying_question_second_head2_text).text="씨앗창고에서 확인해보세요."
        secondDialog.findViewById<TextView>(R.id.dialog_buying_question_second_close_btn).setOnClickListener {
            val a=activity as MainActivity
            a.changeFragtoStoreDetail(seedIdx)
            secondDialog.dismiss()
        }

    }

    override fun onBackPressed() {
        val a=activity as MainActivity
        a.changeFragtoStore()
    }
}