package com.springboard.zzatmari.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.NumberPicker
import android.widget.TimePicker


class CustomTimePicker : TimePicker {
    private val strColor = "#FFFFFF"
    constructor(context: Context?):super(context!!){
        create()
    }
    constructor(context: Context?, atts: AttributeSet?):super(context!!, atts){
        create()
    }
    constructor(context: Context?, atts: AttributeSet?, defStyle: Int):super(context!!, atts, defStyle){

            create()

    }

    @SuppressLint("PrivateApi")
    private fun create()
    {

            val clsParent = Class.forName("com.android.internal.R\$id")
//        for (i in clsParent.fields){
//            Log.d("??",i.toGenericString())
//        }
            val clsAmPm:NumberPicker = findViewById(clsParent.getField("amPm").getInt(null))
//            val clsHour:NumberPicker = findViewById(clsParent.getField("up").getInt(null))
            val clsMin:NumberPicker = findViewById(clsParent.getField("minute").getInt(null))
            val clsNumberPicker = Class.forName("android.widget.NumberPicker")
            val clsSelectionDivider = clsNumberPicker.getDeclaredField("mSelectionDivider")

//            clsSelectionDivider.setAccessible(true)
//            val clsDrawable = ColorDrawable(Color.parseColor(strColor))

            // 오전/오후, 시, 분 구분 라인의 색상을 변경한다.
//            clsSelectionDivider.set(clsAmPm, clsDrawable)
//            clsSelectionDivider.set(clsHour, clsDrawable)
//            clsSelectionDivider.set(clsMin, clsDrawable)


    }
}