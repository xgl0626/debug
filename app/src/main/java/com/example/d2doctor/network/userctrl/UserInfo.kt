package com.example.d2doctor.network.userctrl

import com.example.d2doctor.network.ApiGenerator
import com.example.d2doctor.network.ApiService
import com.example.d2doctor.network.safeSubscribeBy
import com.example.d2doctor.network.setSchedulers
import java.lang.ref.WeakReference

/**
 * Author: RayleighZ
 * Time: 2021-05-13 19:39
 * 用户信息管理，核心需求为token管理
 * 不是很了解后端的token刷新机制
 * 就直接缓存用户的登录信息然后token g了之后就再登陆一次
 */
object UserInfo {
    var token = ""
    var userName = ""
    var password = ""

    var observerArray = ArrayList<WeakReference<TokenReceiver>>()

    fun refreshToken() {
        ApiGenerator.getApiService(ApiService::class.java)
            .login()
            .setSchedulers()
            .safeSubscribeBy {
                for (listener in observerArray) {
                    listener.get()?.let { lis ->
                        lis.receiveToken(token)
                        return@safeSubscribeBy
                    }
                    //走到这里表示wk内部已经为空，可以清除了
                    observerArray.remove(listener)
                }
            }
    }

    fun resignTokenReceiver(tokenReceiver: TokenReceiver) =
        observerArray.add(WeakReference(tokenReceiver))

    public interface TokenReceiver {
        fun receiveToken(token: String)
    }
}