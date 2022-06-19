package com.springboard.zzatmari.src.main.list.goal

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

import com.springboard.zzatmari.R
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ListGoalBottomFragment(val to_do_work: (Int) -> Unit,val goal:String): BottomSheetDialogFragment() {
    var handler = Handler(Looper.getMainLooper())
    var condition=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogStyle)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (dialog is BottomSheetDialog) {
            dialog.behavior.peekHeight=500
            dialog.behavior.skipCollapsed = true
            dialog.behavior.state = STATE_EXPANDED
        }
        return dialog

    }
    var check=false
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val View = inflater.inflate(R.layout.bottom_fragment_goal, container, false)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
//        (dialog as? BottomSheetDialog)?.let {
//            it.behavior.peekHeight = 500
//        }

        return View
    }
    override fun onStart() {
        super.onStart()
//        dialog?.also {
//            val bottomSheet = dialog!!.findViewById<View>(R.id.design_bottom_sheet)
//            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//            val behavior = BottomSheetBehavior.from<View>(bottomSheet)
//            behavior.peekHeight = resources.displayMetrics.heightPixels //replace to whatever you want
//            view?.requestLayout()
//        }
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        val edit=view.findViewById<EditText>(R.id.btm_frag_goal_add_edit)

        val head=view.findViewById<TextView>(R.id.btm_frag_goal_head_text)
        head.text= goal+"의 목표"
        var to_do=""

        edit.setOnClickListener {


        }
        edit.setOnEditorActionListener{ textView, action, event ->
            var handled = false

            if (action == EditorInfo.IME_ACTION_DONE) {
                // 키보드 내리기

                
                val inputMethodManager = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(edit.windowToken, 0)
                handled = true
            }

            handled
        }
        val btn=view.findViewById<TextView>(R.id.btm_frag_goal_add_button)
        Thread(){
            while (condition){

                if (edit.text.toString()==""){
                    handler.post {

                        btn.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_color_opicity_in_tab))
                        btn.background=context?.getDrawable(R.drawable.yellow_opicity_view_radius_16)
                        btn.isClickable=false
                    }
                }
                else{
                    handler.post {
                        btn.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_color_in_tab))
                        btn.background=context?.getDrawable(R.drawable.yellow_view_radius_16)
                        btn.isClickable=true
                    }
                }

                Thread.sleep(100)
            }
        }.start()


        btn.setOnClickListener {
            condition=false
            to_do=edit.text.toString()
            to_do_work(to_do.toInt())
            dialog?.dismiss()
        }
    }

    override fun onDetach() {
        condition=false
        super.onDetach()

    }

}