package com.example.d2doctor.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout

/*
    解决拦截事件的冲突
 */
class MyFrameLayout(context: Context, attr: AttributeSet?) : FrameLayout(context, attr)
{
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }
}