package com.example.d2doctor.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.RequiresApi

/**
 * @Author: 徐国林
 * @ClassName: ClickButtonAnimatorUtils
 * @Description:
 * @Date: 2020/9/12 14:33
 */
object ClickButtonAnimatorUtils {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun buttonAnim(view: View,callBack:AnimEndCallBack?=null) {
        val x = view.width / 2
        val y = view.height / 2
        val radius: Float = view.width.toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(view, x, y, radius, 0f)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                callBack?.callback()
                view.visibility = View.INVISIBLE
            }
        })
        anim.start()
    }
    interface AnimEndCallBack{
        fun callback()
    }
}