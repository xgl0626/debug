package com.example.d2doctor.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import cn.jiguang.dy.Protocol.mContext
import cn.jpush.im.android.api.JMessageClient


/**
 * @Author: xgl
 * @ClassName: NotificationClickEvent
 * @Description:
 * @Date: 2021/1/30 16:36
 */
class NotificationClickEvent : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        JMessageClient.registerEventReceiver(this)
    }

    override fun onDestroy() {
        JMessageClient.unRegisterEventReceiver(this)
        super.onDestroy()
    }

    fun onEvent(event: NotificationClickEvent?) {
        val notificationIntent = Intent(mContext, ChatActivity::class.java)
        mContext.startActivity(notificationIntent) //自定义跳转到指定页面
    }
}