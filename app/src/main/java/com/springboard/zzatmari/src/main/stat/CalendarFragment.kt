package com.springboard.zzatmari.src.main.stat

import android.os.Bundle
import android.view.View
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentCalendarBinding
import com.springboard.zzatmari.src.main.stat.models.GetCalendarResponse


import java.util.*

class CalendarFragment(private val year:Int, private val month:Int, private val getCalendarResponse: GetCalendarResponse):BaseFragment<FragmentCalendarBinding>(FragmentCalendarBinding::bind, R.layout.fragment_calendar) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar= Calendar.getInstance()
        calendar.set(Calendar.MONTH,month)
        calendar.set(Calendar.YEAR,year)
        binding.calendarView.setOnItemClickListener(object :CalendarView.OnItemClickListener{
            override fun onItemClick(v: View, year: Int, month: Int, day: Int) {
                val frag=parentFragment as StatFragment
                frag.changeDailyFrag(year, month, day,"daily",0)
                frag.clickDaily()
            }

        })

        

        binding.calendarView.updateCalendar(calendar,getCalendarResponse)
    }
}