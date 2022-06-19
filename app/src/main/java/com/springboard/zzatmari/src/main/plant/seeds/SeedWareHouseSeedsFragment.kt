package com.springboard.zzatmari.src.main.plant.seeds

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.Application
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentSeedWarehouseSeedsBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.plant.PlantFragment
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseResponse
import com.springboard.zzatmari.src.main.plant.seeds.models.GetSeedWareHouseResult

class SeedWareHouseSeedsFragment:BaseFragment<FragmentSeedWarehouseSeedsBinding>(FragmentSeedWarehouseSeedsBinding::bind, R.layout.fragment_seed_warehouse_seeds),OnBackPressedListener,SeedWareHouseFragmentView {
    var list=ArrayList<GetSeedWareHouseResult>()
    lateinit var service: SeedWareHouseService
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service= SeedWareHouseService(this)

        service.trySeedWareHouse(Application.sSharedPreferences.getInt("userIdx",0))

    }
    override fun onResume() {
        val a=activity as MainActivity
        a.setOnBackPressedListener(this)
        super.onResume()
    }
    override fun onBackPressed() {
        val a=activity as MainActivity
        a.supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, PlantFragment())
                .commitAllowingStateLoss()
    }

    override fun onGetSeedWareHouseSuccess(response: GetSeedWareHouseResponse) {
        if (response.code==1000){
            list=response.result
            if(list.size==0){
                binding.plantSeedWarehouseRv.visibility=View.GONE
                binding.plantSeedWarehouseNoHaveSeedLayout.visibility=View.VISIBLE
            }else{
                binding.plantSeedWarehouseRv.visibility=View.VISIBLE
                binding.plantSeedWarehouseNoHaveSeedLayout.visibility=View.GONE
            }
            binding.plantSeedWarehouseRv.layoutManager= GridLayoutManager(activity,4)
            binding.plantSeedWarehouseRv.setHasFixedSize(true)
            val RvAdapter= SeedWareHouseSeedsRVAdapter(requireContext(),list)
            binding.plantSeedWarehouseRv.adapter=RvAdapter

            RvAdapter.setOnItemClickListener(object : SeedWareHouseSeedsRVAdapter.OnItemClickListener {
                override fun onItemClick(v: View, data: GetSeedWareHouseResult, pos: Int) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.plant_seed_warehouse_frm, SeedWareHouseDetailFragment(data.seedIdx))
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            })

        }else{
            response.message?.let { showCustomToast(it) }
        }
    }

    override fun onGetSeedWareHouseFailure(message: String) {
        showCustomToast(message)
    }

}