package com.example.d2doctor.utils

import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.example.d2doctor.R


object Toast {
    fun toast(content: String,position:Int=Gravity.BOTTOM,xOffset:Int=0,yOffset:Int=100) {
        val view = LayoutInflater.from(MyApplication.context).inflate(R.layout.map_item_toast, null)
        val toast = Toast(MyApplication.context)
        val textView = view.findViewById<TextView>(R.id.map_tv_toast)
        textView.text = content
        toast.setGravity(position, xOffset, yOffset)
        toast.duration = Toast.LENGTH_LONG
        toast.view = view
        toast.show()
    }
}
