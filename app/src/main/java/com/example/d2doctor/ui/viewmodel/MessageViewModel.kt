package com.example.d2doctor.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.jpush.im.android.api.ContactManager
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.callback.GetUserInfoListCallback
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.Message
import cn.jpush.im.android.api.model.UserInfo
import cn.jpush.im.api.BasicCallback
import com.example.d2doctor.config.AppConfig
import com.example.d2doctor.utils.L
import com.example.d2doctor.utils.Toast


class MessageViewModel : ViewModel() {

    val messageData = MutableLiveData<List<Conversation>>()
    val backResult = MutableLiveData<Boolean>()
    val messageResult = MutableLiveData<Boolean>()
    val friendList = MutableLiveData<List<UserInfo>>()

    fun getChatTopicList() {
        messageData.value = JMessageClient.getConversationList()
    }

    fun addFriend(userName: String, content: String) {
        ContactManager.sendInvitationRequest(
            userName,
            AppConfig.appKey,
            content,
            object : BasicCallback() {
                override fun gotResult(responseCode: Int, responseMessage: String) {
                    Log.d("TAG", responseCode.toString() + responseMessage)
                    if (0 == responseCode) {
                        backResult.value = true
                        Toast.toast("success")
                    } else {
                        backResult.value = false
                        Log.d(AppConfig.TAG, responseCode.toString() + responseMessage)
                        Toast.toast("failure")
                    }
                }
            })
    }

    fun getFriendList() {
        ContactManager.getFriendList(object : GetUserInfoListCallback() {
            override fun gotResult(
                responseCode: Int,
                responseMessage: String,
                userInfoList: List<UserInfo>
            ) {
                if (0 == responseCode) {
                    L.v(userInfoList.toString())
                    friendList.value = userInfoList
                    Toast.toast("success")
                } else {
                    Log.d(AppConfig.TAG, responseCode.toString() + responseMessage)
                    Toast.toast("failure")
                }
            }
        })
    }

    fun acceptFriend(targetUserName: String) {
        ContactManager.acceptInvitation(targetUserName, AppConfig.appKey, object : BasicCallback() {
            override fun gotResult(responseCode: Int, responseMessage: String) {
                if (0 == responseCode) {
                    Toast.toast("success")
                } else {
                    Log.d(AppConfig.TAG, responseCode.toString() + responseMessage)
                    Toast.toast("failure")
                }
            }
        })
    }

    fun refusedFriend(targetUserName: String) {
        ContactManager.declineInvitation(
            targetUserName,
            AppConfig.appKey,
            "  ",
            object : BasicCallback() {
                override fun gotResult(responseCode: Int, responseMessage: String) {
                    if (0 == responseCode) {
                        Toast.toast("success")
                    } else {
                        Log.d(AppConfig.TAG, responseCode.toString() + responseMessage)
                        Toast.toast("failure")
                    }
                }
            })
    }

    fun sendMessage(message: Message) {
        JMessageClient.sendMessage(message)
        message.setOnSendCompleteCallback(object : BasicCallback() {
            override fun gotResult(responeCode: Int, responseMessage: String) {
                if (0 == responeCode) {
                    messageResult.value = true
                    Toast.toast("success")
                } else {
                    messageResult.value = false
                    Toast.toast("failure")
                }
            }
        })
    }
}