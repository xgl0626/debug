package com.example.d2doctor.utils

import android.text.TextUtils
import android.util.Log

import cn.jpush.im.android.api.model.UserInfo


/**
 * @Author: xgl
 * @ClassName: L
 * @Description:
 * @Date: 2021/2/17 11:53
 */

object L {
    fun v(info: String) {
        Log.v("TAG===>> ", info)
    }

    fun v(sort: String, info: String) {
        Log.v(sort, info)
    }

    fun v(classz: Class<*>, info: String) {
        Log.v(classz.name + "===>> ", info)
    }

    fun t(msg: String) {
        android.widget.Toast.makeText(MyApplication.context, msg, android.widget.Toast.LENGTH_SHORT)
            .show()
    }

    fun tLong(msg: String) {
        android.widget.Toast.makeText(MyApplication.context, msg, android.widget.Toast.LENGTH_LONG)
            .show()
    }

    fun getName(userInfo: UserInfo?): String {
        if (userInfo == null) {
            return ""
        }
        return if (TextUtils.isEmpty(userInfo.nickname)) {
            userInfo.userName
        } else {
            userInfo.nickname
        }
    }

    fun notNull(list: List<*>?): Boolean {
        return list != null && list.isNotEmpty()
    }
}
