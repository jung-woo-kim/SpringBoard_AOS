package com.springboard.zzatmari.src.main.mypage.store

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentStoreBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.mypage.store.models.GetSeedStoreResponse
import com.springboard.zzatmari.src.main.mypage.store.models.Seed

class StoreFragment:BaseFragment<FragmentStoreBinding>(FragmentStoreBinding::bind, R.layout.fragment_store),StoreFragmentView,OnBackPressedListener {
    lateinit var RvAdapter:StoreRVAdapter
    val service=SeedStoreService(this)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service.tryGetSeedStore()

    }
    override fun onResume() {
        val a=activity as MainActivity
        a.setOnBackPressedListener(this)
        super.onResume()
    }

    @SuppressLint("SetTextI18n")
    override fun onGetSeedStoreSuccess(response: GetSeedStoreResponse) {
        if (response.code==1000){
            binding.mypageShopSunnyNum.text=response.result.mySunlight.toString()+"햇살"
            RvAdapter=StoreRVAdapter(requireContext(),response.result.seedList)

            binding.mypageShopRv.setHasFixedSize(true)
            binding.mypageShopRv.layoutManager=
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            RvAdapter.setOnItemClickListener(object :StoreRVAdapter.OnItemClickListener{
                override fun onItemClick(v: View, data: Seed, pos: Int) {
                    val a=activity as MainActivity
                    a.changeFragtoStoreDetail(data.seedIdx)
                }

            })
            binding.mypageShopRv.adapter=RvAdapter
        }else{
            response.message?.let { showCustomToast(it) }
        }



    }

    override fun onGetSeedStoreFailure(message: String) {
        showCustomToast(message)
    }

    override fun onBackPressed() {
        val a=activity as MainActivity
        a.changeFragtoPlant()
    }
}