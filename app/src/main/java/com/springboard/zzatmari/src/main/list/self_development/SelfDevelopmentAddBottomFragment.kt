package com.springboard.zzatmari.src.main.list.self_development

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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

class SelfDevelopmentAddBottomFragment(val to_do_work: (String) -> Unit): BottomSheetDialogFragment() {
    interface OnItemClickListener{
        fun onItemClick(v:View,string: String):Boolean
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
    var handler = Handler(Looper.getMainLooper())
    var condition=true
    var postCheck=true
    private lateinit var removeDialog:Dialog
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

        val View = inflater.inflate(R.layout.bottom_fragment_change_add, container, false)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        return View
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        condition=true

        super.onViewCreated(view, savedInstanceState)
        val edit=view.findViewById<EditText>(R.id.btm_frag_change_add_edit)

        val head=view.findViewById<TextView>(R.id.bottom_sheet_head_text)
        head.text=requireContext().getString(R.string.self_development_add)
        var to_do=""


        edit.setOnEditorActionListener{ textView, action, event ->
            var handled = false

            if (action == EditorInfo.IME_ACTION_DONE) {
                // 키보드 내리기
                to_do=edit.text.toString()
                val inputMethodManager = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(edit.windowToken, 0)
                handled = true
            }

            handled
        }
        val btn=view.findViewById<TextView>(R.id.btm_frag_change_add_add_button)
        Thread(){
            while (condition){

                if (edit.text.toString()==""){
                    handler.post {

                        btn.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_color_opicity_in_tab))
                        btn.background=requireContext().getDrawable(R.drawable.yellow_opicity_view_radius_16)
                        btn.isClickable=false
                    }
                }
                else{
                    handler.post {
                        btn.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_color_in_tab))
                        btn.background=requireContext().getDrawable(R.drawable.yellow_view_radius_16)
                        btn.isClickable=true
                    }
                }
                if (!postCheck){
                    postCheck=true
                    handler.post {
                        removeDialog=Dialog(requireContext())
                        removeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        removeDialog.setContentView(R.layout.dialog_overlap_list)
                        val params=removeDialog.window?.attributes

                        params?.width=WindowManager.LayoutParams.MATCH_PARENT
                        removeDialog.window?.attributes=params
                        removeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        showDialog()
                    }

                }

                Thread.sleep(100)
            }
        }.start()

        btn.setOnClickListener {
            to_do=edit.text.toString()
            edit.text=null
            to_do_work(to_do)

        }
    }
    fun showDialog(){
        removeDialog.show()
        removeDialog.findViewById<TextView>(R.id.overlap_list_dialog_btn).setOnClickListener {

            removeDialog.dismiss()

        }

    }
    fun setPostCheckFalse(){
        postCheck=false
    }
    fun setConditionFalse(){
        this.condition=false
    }

    override fun onDestroyView() {
        condition=false
        super.onDestroyView()
    }
}