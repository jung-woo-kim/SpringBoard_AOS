package com.springboard.zzatmari.src.main.stat

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentKindsStatsBinding
import com.springboard.zzatmari.src.main.stat.models.CalendarListItem
import com.springboard.zzatmari.src.main.stat.models.GetCalendarListResponse
import com.springboard.zzatmari.util.ToggleAnimation
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class StatKindsFragment(private val year:Int, private val month:Int, private val day:Int, private val type:String,private val continuous:Int):BaseFragment<FragmentKindsStatsBinding>(FragmentKindsStatsBinding::bind, R.layout.fragment_kinds_stats),StatKindsFragmentView {
    var digitalIsExpanding:Boolean=false
    var selfIsExpanding:Boolean=false
    var listDigitalDetox=ArrayList<CalendarListItem>()
    var listSelfDevelopment=ArrayList<CalendarListItem>()
    lateinit var service: StatKindsService
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        service= StatKindsService(this)

        service.tryGetCalendarList(type, year, month, day)

        if (type=="daily") {
            binding.statsKindTextHead.text=day.toString()+"일"
            binding.fragmentKindsStatsRecordContinueLayout.visibility=View.GONE
        }else {
            binding.statsKindTextHead.text=month.toString()+"월"
            binding.fragmentKindsStatsRecordContinueLayout.visibility=View.VISIBLE
            binding.fragmentKindsStatsRecordContinue.text=continuous.toString()+"일"
        }


        binding.fragmentKindsStatsDigitalDetoxBtn.setOnClickListener {
            val show = toggleLayout(!digitalIsExpanding, binding.fragmentKindsStatsGdigitalDetoxArrow, binding.fragmentKindsStatsDigitalDetoxRvLayout)
            digitalIsExpanding = show
            binding.fragmentKindsStatsDigitalDetoxRv.setHasFixedSize(true)
            binding.fragmentKindsStatsDigitalDetoxRv.layoutManager=
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            val RvAdater= StatKindsRVAdapter(requireContext())
            RvAdater.addAllData(listDigitalDetox)

            binding.fragmentKindsStatsDigitalDetoxRv.adapter=RvAdater
        }
        binding.fragmentKindsStatsSelfDevelopmentBtn.setOnClickListener {
            val show = toggleLayout(!selfIsExpanding, binding.fragmentKindsStatsSelfDeveolpmentArrow, binding.fragmentKindsStatsSelfDevelopmentRvLayout)
            selfIsExpanding = show
            binding.fragmentKindsStatsSelfDevelopmentRv.setHasFixedSize(true)
            binding.fragmentKindsStatsSelfDevelopmentRv.layoutManager=
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            val RvAdater=StatKindsRVAdapter(requireContext())
            RvAdater.addAllData(listSelfDevelopment)

            binding.fragmentKindsStatsSelfDevelopmentRv.adapter=RvAdater
        }
    }
    private fun toggleLayout(isExpanded: Boolean, view: View, layoutExpand: LinearLayout): Boolean {
        // 2
        ToggleAnimation.toggleArrow(view, isExpanded)
        if (isExpanded) {
            ToggleAnimation.expand(layoutExpand)
        } else {
            ToggleAnimation.collapse(layoutExpand)
        }
        return isExpanded
    }

    @SuppressLint("SetTextI18n")
    private fun InIt(getCalendarListResponse:GetCalendarListResponse){
        val pieChart:PieChart=binding.pieChart
        pieChart.isDrawHoleEnabled=false
        pieChart.legend.isEnabled=false
        pieChart.setUsePercentValues(false)
        pieChart.setEntryLabelTextSize(11F)
        pieChart.textAlignment=View.TEXT_ALIGNMENT_CENTER
        val colorArray=ArrayList<Int>()
        colorArray.add((context?.resources?.getColor(R.color.zzatrima_main_color_yellow)!!))
        colorArray.add((context?.resources?.getColor(R.color.zzatrima_main_color_pink)!!))
        val entries=ArrayList<PieEntry>()
        var selfPercent=getCalendarListResponse.result.selfDevelopmentPercent
        var digitalPerccent=getCalendarListResponse.result.digitalDetoxPercent
        if (selfPercent==0&&digitalPerccent==0){
            selfPercent=50
            digitalPerccent=50
            binding.pieChart.visibility=View.GONE
            binding.statsKindNoHaveTime.visibility=View.VISIBLE
        }else{
            binding.pieChart.visibility=View.VISIBLE
            binding.statsKindNoHaveTime.visibility=View.GONE
        }
        entries.add(PieEntry(selfPercent.toFloat(),""))
        entries.add(PieEntry(digitalPerccent.toFloat(),""))
        val pieDataSet=PieDataSet(entries,"")
        pieDataSet.apply {
            colors=colorArray
            valueTextColor=context?.resources?.getColor(R.color.black)!!
            valueTextSize=0f
        }
        val pieData= PieData(pieDataSet)
        pieChart.apply {
            data=pieData
            description.isEnabled=false
            isRotationEnabled=false
            centerText=""

            holeRadius=0F
            setUsePercentValues(false)
            setEntryLabelColor(context?.resources?.getColor(R.color.transparent)!!)
        }

        listDigitalDetox=getCalendarListResponse.result.digitalDetox
        listSelfDevelopment=getCalendarListResponse.result.selfDevelopment


        binding.statsKindTextPink.text="디지털디톡스 "+getCalendarListResponse.result.digitalDetoxPercent.toString()+"%"
        binding.statsKindTextYellow.text="자기개발 "+getCalendarListResponse.result.selfDevelopmentPercent.toString()+"%"
        binding.fragmentKindsStatsDigitalDetoxMinText.text=getCalendarListResponse.result.digitalDetoxTime.toString()+" min"
        binding.fragmentKindsStatsSelfDevelopMinText.text=getCalendarListResponse.result.selfDevelopmentTime.toString()+" min"

    }


    override fun onGetCalendarListSuccess(response: GetCalendarListResponse) {
        InIt(response)
        listDigitalDetox=response.result.digitalDetox
        listSelfDevelopment=response.result.selfDevelopment
    }

    override fun onGetCalendarListFailure(message: String) {
        showCustomToast(message)
    }
}