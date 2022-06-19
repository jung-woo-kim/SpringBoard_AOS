package com.springboard.zzatmari.src.main.plant.seeds

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentSeedWarehouseBinding

class SeedWareHouseFragment:BaseFragment<FragmentSeedWarehouseBinding>(FragmentSeedWarehouseBinding::bind,R.layout.fragment_seed_warehouse) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
                .replace(R.id.plant_seed_warehouse_frm, SeedWareHouseSeedsFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }


}