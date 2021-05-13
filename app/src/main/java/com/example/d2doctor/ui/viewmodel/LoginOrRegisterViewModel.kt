package com.example.d2doctor.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.api.BasicCallback
import com.example.d2doctor.utils.MyApplication
import com.example.d2doctor.utils.Toast

/**
 * @Author: 徐国林
 * @ClassName: LoginOrRegisterViewModel
 * @Description:
 * @Date: 2020/9/2 18:02
 */
class LoginOrRegisterViewModel : ViewModel() {
    companion object {
        const val TAG = "LoginOrRegister"
    }

    private var rememberPassword: SharedPreferences = MyApplication.context.getSharedPreferences(
        "remember",
        MODE_PRIVATE
    )

    val result = MutableLiveData<Boolean>()
    fun loginCallback(name: String, password: String) {
        login(name, password, object : BasicCallback() {
            override fun gotResult(int: Int, string: String?) {
                if (int == 0) {
                    result.value = true
                    rememberPassword(name, password)
                } else {
                    Toast.toast(int.toString() + string)
                }
            }
        })
    }

    fun login(name: String, password: String, callback: BasicCallback) {
        JMessageClient.login(name, password, callback)
    }

    @SuppressLint("CommitPrefEdits")
    private fun rememberPassword(name: String, psw: String) {
        val editor = rememberPassword.edit()
        editor?.apply {
            putString("name", name)
            putString("psw", psw)
            apply()
        }
    }

    fun registerCallBack(name: String, password: String, password2: String) {
        if (password == password2) {
            register(name, password, object : BasicCallback() {
                override fun gotResult(int: Int, string: String?) {
                    Log.d(TAG, int.toString() + string.toString())
                    if (int == 0) {
                        Toast.toast("注册成功")
                        loginCallback(name, password)
                    }
                }

            })
        } else
            Toast.toast("两次密码不一样")
    }

    @SuppressLint("CommitPrefEdits")
    fun register(
        name: String,
        password: String,
        callback: BasicCallback
    ) {
        JMessageClient.register(name, password, callback)
    }
}