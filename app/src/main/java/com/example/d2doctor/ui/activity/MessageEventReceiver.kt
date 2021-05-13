package com.example.d2doctor.ui.activity

import android.app.Activity
import android.os.Bundle
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.content.*
import cn.jpush.im.android.api.content.EventNotificationContent.EventNotificationType
import cn.jpush.im.android.api.enums.ContentType
import cn.jpush.im.android.api.event.MessageEvent
import cn.jpush.im.android.api.event.OfflineMessageEvent
import cn.jpush.im.android.api.model.Message


/**
 * @Author: xgl
 * @ClassName: MessageEventReceiver
 * @Description:
 * @Date: 2021/1/30 16:55
 */
class MessageEventReceiver : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //这里只是为了展示注册事件接受者接口的用法，实际上开发者可以在任意类中注册事件接收者
        //，而不仅仅在Activity中。 下同
        JMessageClient.registerEventReceiver(this)
    }

    override fun onDestroy() {
        JMessageClient.unRegisterEventReceiver(this)
        super.onDestroy()
    }

    //用户在线期间收到的消息都会以MessageEvent的方式上抛
    fun onEvent(event: MessageEvent) {
        val msg: Message = event.message
        when (msg.contentType) {
            ContentType.text -> {
                //处理文字消息
                val textContent = msg.content as TextContent
                textContent.text

            }
            ContentType.image -> {
                //处理图片消息
                val imageContent = msg.content as ImageContent
                imageContent.localPath //图片本地地址
                imageContent.localThumbnailPath //图片对应缩略图的本地地址
            }
            ContentType.voice -> {
                //处理语音消息
                val voiceContent = msg.content as VoiceContent
                voiceContent.localPath //语音文件本地地址
                voiceContent.duration //语音文件时长
            }
            ContentType.custom -> {
                //处理自定义消息
                val customContent = msg.content as CustomContent
                customContent.getNumberValue("custom_num") //获取自定义的值
                customContent.getBooleanValue("custom_boolean")
                customContent.getStringValue("custom_string")
            }
            ContentType.eventNotification -> {
                //处理事件提醒消息
                val eventNotificationContent = msg.content as EventNotificationContent
                when (eventNotificationContent.eventNotificationType) {
                    EventNotificationType.group_member_added -> {

                    }
                    EventNotificationType.group_member_removed -> {

                    }
                    EventNotificationType.group_member_exit -> {

                    }
                    EventNotificationType.group_info_updated -> {

                    }
                }
            }
            ContentType.unknown -> {
                // 处理未知消息，未知消息的Content为PromptContent 默认提示文本为“当前版本不支持此类型消息，请更新sdk版本”，上层可选择不处理
                val promptContent = msg.getContent() as PromptContent
                promptContent.promptType //未知消息的type是unknown_msg_type
                promptContent.promptText //提示文本，“当前版本不支持此类型消息，请更新sdk版本”
            }
        }
    }

    //用户离线期间收到的消息会以OfflineMessageEvent的方式上抛，处理方式类似上面的
    //MessageEvent
    fun onEvent(event: OfflineMessageEvent) {
        val msgs: List<Message> = event.offlineMessageList
        for (msg in msgs) {
            //...
        }
    }
}