package com.springboard.zzatmari.src.main.mypage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentMypageBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.mypage.models.GetPlantResponse
import com.springboard.zzatmari.src.main.mypage.models.GetPlantResult

class MyPageFragment:BaseFragment<FragmentMypageBinding>(FragmentMypageBinding::bind, R.layout.fragment_mypage),MyPageFragmentView,OnBackPressedListener {
    private var list=ArrayList<PlantData>()
    lateinit var RvAdapter:MyPageRVAdapter
    private var backKeyPressedTime=0L
    val service=MyPageService(this)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service.tryGetPlant()

    }
    override fun onResume() {
        val a=activity as MainActivity
        a.setOnBackPressedListener(this)
        super.onResume()
    }
    override fun onBackPressed() {
        val a=activity as MainActivity
        a.changeFragtoMyPageAccount()
    }

    override fun onGetPlantSuccess(response: GetPlantResponse) {
        if (response.code==1000){
            var i=0
            var temp=ArrayList<GetPlantResult>()
            while (i<response.result.size){
                temp.add(response.result[i])

                if (i%3==2){
                    list.add(PlantData(temp[0].plantImgUrl,temp[1].plantImgUrl,temp[2].plantImgUrl))
                    temp=ArrayList()
                }
                i++
            }
            if (temp.size==2){
                list.add(PlantData(temp[0].plantImgUrl,temp[1].plantImgUrl,""))
            }else if(temp.size==1){
                list.add(PlantData(temp[0].plantImgUrl,"",""))
            }
            if (response.result.size in 4..6){
                list.add(PlantData("","",""))
            }else if(response.result.size in 1..3){
                list.add(PlantData("","",""))
                list.add(PlantData("","",""))
            }else if(response.result.size==0) {
                list.add(PlantData("", "", ""))
                list.add(PlantData("", "", ""))
                list.add(PlantData("", "", ""))
            }


            RvAdapter= MyPageRVAdapter(requireContext(),list)

            binding.mypageRv.setHasFixedSize(true)
            binding.mypageRv.layoutManager=
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            RvAdapter.setOnItemClickListener(object : StoreRVAdapter.OnItemClickListener{
//                override fun onItemClick(v: View, data: Seed, pos: Int) {
//                    val a=activity as MainActivity
//                    a.changeFragtoStoreDetail(data.seedIdx)
//                }
//
//            })
            binding.mypageRv.adapter=RvAdapter
        }else{
            response.message?.let { showCustomToast(it) }
        }
    }

    override fun onGetPlantFailure(message: String) {
        showCustomToast(message)
    }
}