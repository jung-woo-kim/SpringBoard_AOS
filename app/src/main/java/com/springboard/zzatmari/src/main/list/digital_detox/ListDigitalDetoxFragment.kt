package com.springboard.zzatmari.src.main.list.digital_detox

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseFragment
import com.springboard.zzatmari.databinding.FragmentListDigitalDetoxBinding
import com.springboard.zzatmari.src.main.MainActivity
import com.springboard.zzatmari.src.main.OnBackPressedListener
import com.springboard.zzatmari.src.main.list.ListItemActivityView
import com.springboard.zzatmari.src.main.list.ListItemService
import com.springboard.zzatmari.src.main.list.models.*
import java.lang.Thread.sleep

class ListDigitalDetoxFragment:BaseFragment<FragmentListDigitalDetoxBinding>(FragmentListDigitalDetoxBinding::bind,R.layout.fragment_list_digital_detox),ListItemActivityView,OnBackPressedListener{
    private var list=ArrayList<GetListItemResult>()
    private var condition = true
    private var viewVisibleCheck=false
    private lateinit var thread: Thread
    private var backKeyPressedTime=0L
    private lateinit var RvAdater:ListDigitalDetoxRVAdapter
    private var handler = Handler(Looper.getMainLooper())
    private val service=ListItemService(this)
    private lateinit var removeDialog: Dialog
    private lateinit var listName:String
    private lateinit var digitalDetoxAddBottomDialogFragment: DigitalDetoxAddBottomFragment
    private lateinit var digitalDetoxChangeBottomDialogFragment: DigitalDetoxChangeBottomFragment
    private var listIdx:Int=0
    private var position:Int=0
    private var time:Int=0
    private var goalTime=0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeDialog= Dialog(requireContext())
        removeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        removeDialog.setContentView(R.layout.dialog_remove_list)
        val params=removeDialog.window?.attributes

        params?.width=WindowManager.LayoutParams.MATCH_PARENT
        removeDialog.window?.attributes=params

        removeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.deleteBtn.setOnClickListener {
            showDialog()
        }
        digitalDetoxAddBottomDialogFragment= DigitalDetoxAddBottomFragment {
            listName=it
            service.tryPostListAdd(PostListAddRequest(0,it))
        }

        digitalDetoxAddBottomDialogFragment.setOnItemClickListener(object :DigitalDetoxAddBottomFragment.OnItemClickListener{
            override fun onItemClick(v: View,string: String): Boolean {

                return true
            }

        })
        binding.digitalDetoxAddBtn.setOnClickListener {

            digitalDetoxAddBottomDialogFragment.show(childFragmentManager, digitalDetoxAddBottomDialogFragment.tag)
        }
        digitalDetoxChangeBottomDialogFragment = DigitalDetoxChangeBottomFragment {
            listName=it
            service.tryPatchListChange(listIdx,PatchListChangeRequest(it))
        }
        digitalDetoxChangeBottomDialogFragment.setOnItemClickListener(object :DigitalDetoxChangeBottomFragment.OnItemClickListener{
            override fun onItemClick(v: View,string: String): Boolean {

                return true//수정이 성공했으면 true!
            }

        })
        binding.changeBtn.setOnClickListener {

            digitalDetoxChangeBottomDialogFragment.show(childFragmentManager, digitalDetoxChangeBottomDialogFragment.tag)
        }

        service.tryGetListItem(0)

        thread=Thread(){
            while (condition){
                val activity:MainActivity= context as MainActivity
                if(activity.getGesture()){
                    viewVisibleCheck=false
                    activity.check=false
                    handler.post {

                        refreshRV()
                    }

                }
                sleep(10)
            }
        }
        thread.start()

    }
    private fun refreshRV(){

        binding.deleteChangeView.visibility=View.GONE
        binding.digitalDetoxAddBtn.visibility=View.VISIBLE

        binding.listDigitalDetoxRv.setHasFixedSize(true)
        binding.listDigitalDetoxRv.layoutManager=
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        RvAdater= ListDigitalDetoxRVAdapter(requireContext(),list)
        RvAdater.setOnItemLongClickListener(object :ListDigitalDetoxRVAdapter.OnItemLongClickListener{
            override fun onItemLongClick(v: View, data: GetListItemResult, pos: Int) {
                if (!viewVisibleCheck){
                    goalTime=data.goalTime
                    listIdx=data.listIdx
                    position=pos
                    time=data.time
                    viewVisibleCheck=true
                    binding.deleteChangeView.visibility=View.VISIBLE
                    binding.digitalDetoxAddBtn.visibility=View.GONE
                    v.findViewById<TextView>(R.id.row_digital_detox_text_view).setTypeface(v.findViewById<TextView>(R.id.row_digital_detox_text_view).typeface,Typeface.BOLD)
                }
            }

        })

        binding.listDigitalDetoxRv.adapter=RvAdater
        if (list.size>0){
            binding.listDigitalDetoxRv.visibility=View.VISIBLE
            binding.emptyListText.visibility=View.INVISIBLE
        }else{
            binding.listDigitalDetoxRv.visibility=View.INVISIBLE
            binding.emptyListText.visibility=View.VISIBLE
        }
    }



    override fun onGetItemSuccess(response: GetListItemResponse) {
        if (response.isSuccess){
            for(i in response.result){
                list.add(i)
            }

        }else{
            response.message?.let { showCustomToast(message = it) }
        }
        refreshRV()

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

        }else if(response.code==3020){
            digitalDetoxAddBottomDialogFragment.setPostCode(response.code)
        }else{
            response.message?.let { showCustomToast(it) }
        }
        if (list.size>0){
            binding.listDigitalDetoxRv.visibility=View.VISIBLE
            binding.emptyListText.visibility=View.INVISIBLE
        }else{
            binding.listDigitalDetoxRv.visibility=View.INVISIBLE
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

            list.set(position,GetListItemResult(listIdx,listName,time,goalTime))
            refreshRV()
            viewVisibleCheck=false
            binding.deleteChangeView.visibility=View.GONE
            binding.digitalDetoxAddBtn.visibility=View.VISIBLE

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
            binding.listDigitalDetoxRv.visibility=View.VISIBLE
            binding.emptyListText.visibility=View.INVISIBLE
        }else{
            binding.listDigitalDetoxRv.visibility=View.INVISIBLE
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
            binding.digitalDetoxAddBtn.visibility=View.VISIBLE
            refreshRV()
        }else{
            if(System.currentTimeMillis() > backKeyPressedTime + 2000){
                backKeyPressedTime = System.currentTimeMillis()
                showCustomToast("한번 더 누를시 종료됩니다.")
                return
            }
            if(System.currentTimeMillis() <= backKeyPressedTime + 2000){
                a.finish()
            }



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
            binding.digitalDetoxAddBtn.visibility=View.VISIBLE
            viewVisibleCheck=false
            removeDialog.dismiss()
        }
    }

    override fun onPause() {
        condition=false
        super.onPause()
    }
    override fun onDestroyView() {
        condition=false
        super.onDestroyView()
    }

}