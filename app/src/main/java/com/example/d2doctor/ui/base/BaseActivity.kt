package com.example.d2doctor.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.event.ContactNotifyEvent
import cn.jpush.im.android.api.event.LoginStateChangeEvent
import com.example.d2doctor.utils.L


/**
 * @Author: xgl
 * @ClassName: BaseActivity
 * @Description:
 * @Date: 2021/2/17 11:52
 */

abstract class BaseActivity : AppCompatActivity() {
    companion object {
        var friendApply: MutableList<ContactNotifyEvent> = ArrayList()
    }

    abstract fun getLayoutId(): Int
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //竖屏
        setContentView(getLayoutId())


        //订阅接收消息,子类只要重写onEvent就能收到消息
        JMessageClient.registerEventReceiver(this)
        initData()
        initView()
    }

    protected abstract fun initView()
    protected abstract fun initData()
    override fun onDestroy() {
        super.onDestroy()

        //注销消息接收
        JMessageClient.unRegisterEventReceiver(this)
    }

    fun onEventMainThread(event: LoginStateChangeEvent) {
        val reason = event.reason
        if (reason == LoginStateChangeEvent.Reason.user_logout) {
            L.t("登录失效，重新登录")
        } else if (reason == LoginStateChangeEvent.Reason.user_password_change) {
            L.t("修改密码，重新登录")
        }
        if (!isFinishing) {
            JMessageClient.logout()
        }
    }

    fun onEvent(event: ContactNotifyEvent) {
        if (event.type == ContactNotifyEvent.Type.invite_received) {
            friendApply.add(event)
            L.t("收到了好友邀请")
        }
    }

}
