package com.springboard.zzatmari.src.main.list.goal

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentListGoalBinding
import com.springboard.zzatmari.src.main.list.goal.models.GetGoalItemResponse
import com.springboard.zzatmari.src.main.list.goal.models.PostGoalRegisterRequest
import com.springboard.zzatmari.src.main.list.goal.models.PostGoalRegisterResponse
import com.springboard.zzatmari.src.main.list.models.GetListItemResult
import com.springboard.zzatmari.util.ToggleAnimation

class ListGoalFragment:BaseFragment<FragmentListGoalBinding>(FragmentListGoalBinding::bind,R.layout.fragment_list_goal),ListGoalFragmentView{
    private var digitalIsExpanding:Boolean=false
    private var selfIsExpanding:Boolean=false
    lateinit var digitalDetoxRVAdapter:ListGoalRVAdapter
    lateinit var selfDevelopRVAdapter:ListGoalRVAdapter
    private val service=ListGoalService(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service.tryGetListItem()
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
    override fun onGetGoalItemSuccess(response: GetGoalItemResponse) {
        var total_min=0
        for (i in response.result.digitalDetox){
            total_min+=i.time
        }
        binding.goalDigitalDetoxMinText.text= "$total_min min"
        total_min=0
        for (i in response.result.selfDevelopment){
            total_min+=i.time
        }
        binding.goalSelfDevelopMinText.text= "$total_min min"

        binding.listGoalDigitalDetoxRv.setHasFixedSize(true)
        binding.listGoalDigitalDetoxRv.layoutManager=
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        digitalDetoxRVAdapter=ListGoalRVAdapter(requireContext(),response.result.digitalDetox)
        digitalDetoxRVAdapter.setOnItemClickListener(object :ListGoalRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: GetListItemResult, pos: Int) {
                val ListGoalBottomDialogFragment: ListGoalBottomFragment = ListGoalBottomFragment({service.tryPostGoalRegister(PostGoalRegisterRequest(data.listIdx,it))},data.listItem)
                ListGoalBottomDialogFragment.show(childFragmentManager, ListGoalBottomDialogFragment.tag)

            }
        })
        binding.listGoalDigitalDetoxRv.adapter=digitalDetoxRVAdapter
        binding.listGoalDigitalDetoxBtn.setOnClickListener {
            val show = toggleLayout(!digitalIsExpanding, binding.digitalDetoxArrow, binding.listGoalDigitalDetoxRvLayout)
            digitalIsExpanding = show
        }



        binding.listGoalSelfDevelopmentRv.setHasFixedSize(true)
        binding.listGoalSelfDevelopmentRv.layoutManager=
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        selfDevelopRVAdapter=ListGoalRVAdapter(requireContext(),response.result.selfDevelopment)
        selfDevelopRVAdapter.setOnItemClickListener(object :ListGoalRVAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: GetListItemResult, pos: Int) {
                val ListGoalBottomDialogFragment: ListGoalBottomFragment = ListGoalBottomFragment({service.tryPostGoalRegister(PostGoalRegisterRequest(data.listIdx,it))},data.listItem)
                ListGoalBottomDialogFragment.show(childFragmentManager, ListGoalBottomDialogFragment.tag)
            }

        })
        binding.listGoalSelfDevelopmentRv.adapter=selfDevelopRVAdapter


        binding.listGoalSelfDevelopmentBtn.setOnClickListener {

            val show = toggleLayout(!selfIsExpanding, binding.selfDeveolpmentArrow, binding.listGoalSelfDevelopmentRvLayout)
            selfIsExpanding = show


        }


    }

    override fun onGetGoalItemFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPostGoalRegisterSuccess(response: PostGoalRegisterResponse) {
        service.tryGetListItem()
    }
    override fun onPostGoalRegisterFailure(message: String) {
        showCustomToast(message)
    }
}