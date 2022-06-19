package com.springboard.zzatmari.src.main.list.timer.list

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.springboard.zzatmari.R
import com.springboard.zzatmari.config.BaseActivity
import com.springboard.zzatmari.databinding.ActivityTimerListBinding
import com.springboard.zzatmari.src.main.list.timer.add.TimerAddActivity
import com.springboard.zzatmari.src.main.list.timer.change.TimerChangeActivity
import com.springboard.zzatmari.src.main.list.timer.list.models.DeleteTimerListResponse
import com.springboard.zzatmari.src.main.list.timer.list.models.GetTimerListResponse
import com.springboard.zzatmari.src.main.list.timer.list.models.GetTimerListResult

class TimerListActivity:BaseActivity<ActivityTimerListBinding>(ActivityTimerListBinding::inflate), TimerListActivityView {
    private var viewVisibleCheck=false
    private var list=ArrayList<GetTimerListResult>()
    private var condition = true
    private lateinit var thread: Thread
    private lateinit var RvAdapter: TimerListRVAdapter
    private val service= TimerListService(this)
    private var handler = Handler(Looper.getMainLooper())
    private var timerIdx:Int=0
    private var position:Int=0
    private var listIdx=0
    private lateinit var removeDialog:Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeDialog=Dialog(this)
        removeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        removeDialog.setContentView(R.layout.dialog_remove_timer)
        val params=removeDialog.window?.attributes

        params?.width= WindowManager.LayoutParams.MATCH_PARENT
        removeDialog.window?.attributes=params
        removeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.deleteBtn.setOnClickListener {
            showDialog()
        }
        listIdx=intent.getIntExtra("listIdx",0)

        binding.timerAddBtn.setOnClickListener {
            val intent= Intent(this, TimerAddActivity::class.java)
            startActivity(intent)
        }
        binding.changeBtn.setOnClickListener {
            val intent= Intent(this, TimerChangeActivity::class.java)
            intent.putExtra("timerIdx", timerIdx)
            startActivity(intent)
        }
        service.tryGetTimerListItem()
        thread=Thread{
            while (condition){
                if(check){
                    check=false
                    handler.post {
                        unlongclick()
                        viewVisibleCheck=false
                        refreshRV()
                    }
                }
                Thread.sleep(100)
            }
        }
        thread.start()
    }

    override fun onBackPressed() {
        if (viewVisibleCheck){
            unlongclick()
            viewVisibleCheck=false
            refreshRV()
        }else{
            super.onBackPressed()
        }

    }

    private fun longclick(){
        viewVisibleCheck=true
        binding.deleteChangeView.visibility=View.VISIBLE
        binding.timerAddBtn.visibility=View.GONE
    }
    private fun unlongclick(){
        viewVisibleCheck=false
        binding.deleteChangeView.visibility=View.GONE
        binding.timerAddBtn.visibility=View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        service.tryGetTimerListItem()
    }
    override fun onGetTimerSuccess(response: GetTimerListResponse) {
        list=response.result
        binding.timerListRv.setHasFixedSize(true)
        binding.timerListRv.layoutManager=
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        RvAdapter= TimerListRVAdapter(this, list,listIdx)
        RvAdapter.setOnItemClickListener(object : TimerListRVAdapter.OnItemLongClickListener {

            override fun onItemLongClick(v: View, data: GetTimerListResult, pos: Int) {
                if (!viewVisibleCheck){
                    position=pos
                    viewVisibleCheck = true
                    longclick()
                    timerIdx = data.timerIdx
                    v.findViewById<TextView>(R.id.to_do_hour_text).setTypeface(v.findViewById<TextView>(R.id.to_do_hour_text).typeface, Typeface.BOLD)
                    v.findViewById<TextView>(R.id.to_do_minute_text).setTypeface(v.findViewById<TextView>(R.id.to_do_minute_text).typeface, Typeface.BOLD)
                }

            }
        })

        binding.timerListRv.adapter=RvAdapter
//        refreshRV(list)
//        RvAdapter.setData(list)

    }

    override fun onGetTimerFailure(message: String) {
        showCustomToast(message)
    }

    override fun onDeleteTimerSuccess(response: DeleteTimerListResponse) {
        if (response.code==1000){
            showCustomToast("삭제 완료!")
        }else{
            response.message?.let { showCustomToast(it) }
        }
    }

    override fun onDeleteTimerFailure(message: String) {
        showCustomToast(message)
    }

    private var check=false

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val action: Int = MotionEventCompat.getActionMasked(event)

        return when (action) {
            MotionEvent.ACTION_DOWN -> {
                check = true
                true
            }
            MotionEvent.ACTION_MOVE -> {
                check = true
                true
            }
            MotionEvent.ACTION_UP -> {
                check = true
                true
            }
            MotionEvent.ACTION_CANCEL -> {
                check = true
                true
            }
            MotionEvent.ACTION_OUTSIDE -> {
                check = true
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
    private fun refreshRV(){
        binding.timerListRv.setHasFixedSize(true)
        binding.timerListRv.layoutManager=
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        RvAdapter= TimerListRVAdapter(this, list,listIdx)
        RvAdapter.setOnItemClickListener(object : TimerListRVAdapter.OnItemLongClickListener {

            override fun onItemLongClick(v: View, data: GetTimerListResult, pos: Int) {
                if (!viewVisibleCheck){
                    position=pos
                    viewVisibleCheck = true
                    longclick()
                    timerIdx = data.timerIdx
                    v.findViewById<TextView>(R.id.to_do_hour_text).setTypeface(v.findViewById<TextView>(R.id.to_do_hour_text).typeface, Typeface.BOLD)
                    v.findViewById<TextView>(R.id.to_do_minute_text).setTypeface(v.findViewById<TextView>(R.id.to_do_minute_text).typeface, Typeface.BOLD)
                }

            }
        })

        binding.timerListRv.adapter=RvAdapter
    }
    fun showDialog(){
        removeDialog.show()
        removeDialog.findViewById<TextView>(R.id.remove_timer_dialog_no_btn).setOnClickListener {
            check=true
            unlongclick()
            removeDialog.dismiss()

        }
        removeDialog.findViewById<TextView>(R.id.remove_timer_dialog_yes_btn).setOnClickListener {
            service.tryDelteTimerListItem(timerIdx)
            unlongclick()
            list.removeAt(position)
            RvAdapter.notifyItemRemoved(position)
            RvAdapter.notifyItemRangeChanged(position, list.size)
            removeDialog.dismiss()
        }
    }

    override fun onPause() {
        super.onPause()
        unlongclick()
    }

}