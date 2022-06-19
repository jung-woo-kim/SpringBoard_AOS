package com.springboard.zzatmari.src.main.list.self_development

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentListSelfDevelopmentBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.list.ListItemActivityView
import com.springboard.zzatmari.src.main.list.ListItemService
import com.springboard.zzatmari.src.main.list.models.*

class ListSelfDevelopmentFragment:BaseFragment<FragmentListSelfDevelopmentBinding>(FragmentListSelfDevelopmentBinding::bind,R.layout.fragment_list_self_development),ListItemActivityView,OnBackPressedListener{
    private var list=ArrayList<GetListItemResult>()
    private lateinit var RvAdater: ListSelfDevelopmentRVAdapter
    private var condition = true
    private var viewVisibleCheck=false
    private lateinit var thread: Thread
    private var backKeyPressedTime=0L
    private var handler = Handler(Looper.getMainLooper())
    private var check=false
    private val service=ListItemService(this)
    private var postCheck=false
    private lateinit var removeDialog:Dialog
    private lateinit var digitalDetoxAddBottomDialogFragment: SelfDevelopmentAddBottomFragment
    private lateinit var digitalDetoxChangeBottomDialogFragment: SelfDevelopmentChangeBottomFragment
    private var listIdx:Int=0
    private var position:Int=0
    private var time=0
    private lateinit var listName:String
    private var goalTime=0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        condition=true
        removeDialog= Dialog(requireContext())
        removeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        removeDialog.setContentView(R.layout.dialog_remove_list)
        val params=removeDialog.window?.attributes

        params?.width= WindowManager.LayoutParams.MATCH_PARENT
        removeDialog.window?.attributes=params
        removeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.deleteBtn.setOnClickListener {
            showDialog()
        }
        digitalDetoxAddBottomDialogFragment= SelfDevelopmentAddBottomFragment {
            listName=it
            service.tryPostListAdd(PostListAddRequest(1,it))

        }
        digitalDetoxAddBottomDialogFragment.setOnItemClickListener(object : SelfDevelopmentAddBottomFragment.OnItemClickListener{
            override fun onItemClick(v: View,string: String): Boolean {

                return postCheck//수정이 성공했으면 true!
            }

        })
        binding.selfDevelopmentAddBtn.setOnClickListener {

            digitalDetoxAddBottomDialogFragment.show(childFragmentManager, digitalDetoxAddBottomDialogFragment.tag)

        }
        digitalDetoxChangeBottomDialogFragment= SelfDevelopmentChangeBottomFragment {
            listName=it
            service.tryPatchListChange(listIdx,PatchListChangeRequest(it))
        }
        digitalDetoxChangeBottomDialogFragment.setOnItemClickListener(object : SelfDevelopmentChangeBottomFragment.OnItemClickListener{
            override fun onItemClick(v: View,string: String): Boolean {

                return true//수정이 성공했으면 true!
            }

        })
        binding.changeBtn.setOnClickListener {

            digitalDetoxChangeBottomDialogFragment.show(childFragmentManager, digitalDetoxChangeBottomDialogFragment.tag)
        }

        service.tryGetListItem(1)

        thread=Thread(){
            while (condition){
                val activity: MainActivity = context as MainActivity
                if(activity.getGesture()){
                    viewVisibleCheck=false
                    activity.check=false
                    handler.post {


                        refreshRV()
                    }

                }
                Thread.sleep(10)
            }
        }
        thread.start()
    }


    override fun onGetItemSuccess(response: GetListItemResponse) {
        if (response.isSuccess){
            for(i in response.result){
                list.add(i)
            }
        }else{
            response.message?.let { showCustomToast(it) }
        }


            binding.listSelfDevelopmentRv.layoutManager=
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            binding.listSelfDevelopmentRv.setHasFixedSize(true)
            RvAdater=ListSelfDevelopmentRVAdapter(requireContext(),list)
            RvAdater.setOnItemClickListener(object :ListSelfDevelopmentRVAdapter.OnItemLongClickListener{
                override fun onItemLongClick(v: View, data: GetListItemResult, pos: Int) {
                    if (!viewVisibleCheck){
                        goalTime=data.goalTime
                        time=data.time
                        position=pos
                        listIdx=data.listIdx
                        viewVisibleCheck=true
                        binding.deleteChangeView.visibility=View.VISIBLE
                        binding.selfDevelopmentAddBtn.visibility=View.GONE
                        v.findViewById<TextView>(R.id.row_self_develop_text_view).setTypeface(v.findViewById<TextView>(R.id.row_self_develop_text_view).typeface, Typeface.BOLD)
                    }
                }

            })
            binding.listSelfDevelopmentRv.adapter=RvAdater
        if (list.size>0){
            binding.listSelfDevelopmentRv.visibility=View.VISIBLE
            binding.emptyListText.visibility=View.INVISIBLE
        }else{
            binding.listSelfDevelopmentRv.visibility=View.INVISIBLE
            binding.emptyListText.visibility=View.VISIBLE
        }



    }
    override fun onGetItemFailure(message: String) {
        showCustomToast("에러")
    }

    override fun onPostListAddSuccess(response: PostListAddResponse) {
        if (response.code==1000){
            list.add(GetListItemResult(response.result.listIdx,listName,0,0))
            RvAdater.notifyItemInserted(list.size-1)
            digitalDetoxAddBottomDialogFragment.setConditionFalse()
            digitalDetoxAddBottomDialogFragment.dismiss()
//            refreshRV()
        }else{
            digitalDetoxAddBottomDialogFragment.setPostCheckFalse()
        }
        if (list.size>0){
            binding.listSelfDevelopmentRv.visibility=View.VISIBLE
            binding.emptyListText.visibility=View.INVISIBLE
        }else{
            binding.listSelfDevelopmentRv.visibility=View.INVISIBLE
            binding.emptyListText.visibility=View.VISIBLE
        }
    }

    override fun onPostListAddFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPatchListChangeSuccess(response: PatchListChangeResponse) {
        if (response.code==1000){


            digitalDetoxChangeBottomDialogFragment.setConditionFalse()
            digitalDetoxChangeBottomDialogFragment.dismiss()

            list[position] = GetListItemResult(listIdx,listName,time,goalTime)
            refreshRV()
            viewVisibleCheck=false
            binding.deleteChangeView.visibility=View.GONE
            binding.selfDevelopmentAddBtn.visibility=View.VISIBLE

        }else if (response.code==3020 || response.code==3021){
            digitalDetoxChangeBottomDialogFragment.setPostCode(response.code)
        }else{
            response.message?.let { showCustomToast(it) }
        }
    }

    override fun onPatchListChangeFailure(message: String) {
        showCustomToast(message)
    }

    override fun onPatchListRemoveSuccess(response: PatchListRemoveResponse) {
        if (response.code==1000){
            list.removeAt(position)
            RvAdater.notifyItemRemoved(position)
            RvAdater.notifyItemRangeChanged(position, list.size)
        }else{
            response.message?.let { showCustomToast(it) }
        }
        if (list.size>0){
            binding.listSelfDevelopmentRv.visibility=View.VISIBLE
            binding.emptyListText.visibility=View.INVISIBLE
        }else{
            binding.listSelfDevelopmentRv.visibility=View.INVISIBLE
            binding.emptyListText.visibility=View.VISIBLE
        }

    }

    override fun onPatchListRemoveFailure(message: String) {
        showCustomToast(message)
    }

    override fun onResume() {
        val a=activity as MainActivity
        a.setOnBackPressedListener(this)
        super.onResume()
    }
    override fun onBackPressed() {
        val a=activity as MainActivity
        if (viewVisibleCheck){
            viewVisibleCheck=false
            binding.deleteChangeView.visibility=View.GONE
            binding.selfDevelopmentAddBtn.visibility=View.VISIBLE
            refreshRV()
        }else{
            if(System.currentTimeMillis() > backKeyPressedTime + 2000){
                backKeyPressedTime = System.currentTimeMillis();
                showCustomToast("한번 더 누를시 종료됩니다.")
                return
            }
            if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
                a.finish()
            }



        }
    }
    private fun refreshRV(){
            binding.deleteChangeView.visibility=View.GONE
            binding.selfDevelopmentAddBtn.visibility=View.VISIBLE
            binding.listSelfDevelopmentRv.layoutManager=
                    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            binding.listSelfDevelopmentRv.setHasFixedSize(true)
            RvAdater=ListSelfDevelopmentRVAdapter(requireContext(),list)
            RvAdater.setOnItemClickListener(object :ListSelfDevelopmentRVAdapter.OnItemLongClickListener{
                override fun onItemLongClick(v: View, data: GetListItemResult, pos: Int) {
                    if (!viewVisibleCheck){
                        goalTime=data.goalTime
                        time=data.time
                        position=pos
                        listIdx=data.listIdx
                        viewVisibleCheck=true
                        binding.deleteChangeView.visibility=View.VISIBLE
                        binding.selfDevelopmentAddBtn.visibility=View.GONE
                        v.findViewById<TextView>(R.id.row_self_develop_text_view).setTypeface(v.findViewById<TextView>(R.id.row_self_develop_text_view).typeface, Typeface.BOLD)
                    }
                }

            })
            binding.listSelfDevelopmentRv.adapter=RvAdater

        if (list.size>0){
            binding.listSelfDevelopmentRv.visibility=View.VISIBLE
            binding.emptyListText.visibility=View.INVISIBLE
        }else{
            binding.listSelfDevelopmentRv.visibility=View.INVISIBLE
            binding.emptyListText.visibility=View.VISIBLE
        }


    }
    fun showDialog(){
        removeDialog.show()
        removeDialog.findViewById<TextView>(R.id.remove_list_dialog_no_btn).setOnClickListener {
            removeDialog.dismiss()

        }
        removeDialog.findViewById<TextView>(R.id.remove_list_dialog_yes_btn).setOnClickListener {
            service.tryPatchListRemove(listIdx)

            binding.deleteChangeView.visibility=View.GONE
            binding.selfDevelopmentAddBtn.visibility=View.VISIBLE
            viewVisibleCheck=false
            removeDialog.dismiss()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        condition=false
    }
}