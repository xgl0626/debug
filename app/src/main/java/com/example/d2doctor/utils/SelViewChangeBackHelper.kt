package com.example.d2doctor.utils

import android.view.View

/**
 * Author: RayleighZ
 * Time: 2021-04-12 19:25
 */
class SelViewChangeBackHelper(private val viewList: List<View>, val selRes: Int, val unSelRes: Int) {

    var curSelView = viewList[0]

    fun changeViewBack(targetView: View){
        targetView.setBackgroundResource(selRes)
        curSelView.setBackgroundResource(unSelRes)
        curSelView = targetView
    }
}