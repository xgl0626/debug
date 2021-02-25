package com.example.d2doctor.ui.activity

import android.annotation.SuppressLint
import android.os.Handler
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.content.CustomContent
import cn.jpush.im.android.api.content.PromptContent
import cn.jpush.im.android.api.enums.ContentType
import cn.jpush.im.android.api.enums.ConversationType
import cn.jpush.im.android.api.enums.MessageDirect
import cn.jpush.im.android.api.enums.MessageStatus
import cn.jpush.im.android.api.event.MessageEvent
import cn.jpush.im.android.api.event.OfflineMessageEvent
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.GroupInfo
import cn.jpush.im.android.api.model.Message
import cn.jpush.im.android.api.model.UserInfo
import com.example.d2doctor.R
import com.example.d2doctor.bean.ChatBean
import com.example.d2doctor.config.AppConfig
import com.example.d2doctor.config.AppConfig.SCROLL_BOTTOM
import com.example.d2doctor.ui.adapter.ChatMessageAdapter
import com.example.d2doctor.ui.base.BaseActivity
import com.example.d2doctor.ui.viewmodel.MessageViewModel
import com.example.d2doctor.utils.L
import kotlinx.android.synthetic.main.activity_chat.*

/**
 * @Author: 徐国林
 * @ClassName: ChatActivity
 * @Description:
 * @Date: 2020/9/6 13:28
 */
class ChatActivity : BaseActivity() {
    private var userName: String? = null
    private var viewModel: MessageViewModel? = null
    private var adapter: ChatMessageAdapter? = null
    private var type: String? = null
    private var conversation: Conversation? = null
    var list: MutableList<ChatBean> = mutableListOf()
    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            super.handleMessage(msg)
            when (msg?.what) {
                SCROLL_BOTTOM -> {
                    adapter?.addData(list)
                    recyclerview.scrollToPosition(list.size - 1)
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    private fun initRefreshMeg() {

    }

    private fun initClick() {
        tv_chat_send.setOnClickListener {
            val message: Message = JMessageClient.createSingleTextMessage(
                userName,
                AppConfig.appKey,
                et_chat_msg.text.toString()
            )
            viewModel?.sendMessage(message)
            adapter?.addData(ChatBean(message, AppConfig.TEXT_SEND))
        }
        viewModel?.messageResult?.observe(this, Observer {
            if (it) {
                et_chat_msg.setText("")
            }
        })
    }

    override fun initData() {
        viewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
        userName = intent.getStringExtra("username")
        type = intent.getStringExtra("message")
        if (type == AppConfig.SINGLEMESSAGE) {
            conversation = JMessageClient.getSingleConversation(userName)
            if (conversation == null) {
                conversation = Conversation.createSingleConversation(userName)
            }
        }
        adapter = ChatMessageAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        getAllMessage()
        srl_chat.setOnRefreshListener {
            getAllMessage()
            srl_chat.isRefreshing = false
        }
        initClick()
        initRefreshMeg()

    }

    fun getAllMessage() {
        //获取通话记录
        if (conversation?.allMessage != null) {
            for (bean in conversation?.allMessage!!.toMutableList()) {
                addMessage(bean)
            }
        }
    }

    //接受了在线信息

    fun onEventMainThread(event: MessageEvent) {
        addMessage(event.message)
    }

    //离线消息
    fun onEvent(event: OfflineMessageEvent) {
        for (bean in event.offlineMessageList) {
            addMessage(bean)
        }

    }

    //消息加入和刷新界面
    fun addMessage(message: Message) {
        if (message.status == MessageStatus.send_fail) {
            return
        }
        if (message.contentType == ContentType.eventNotification) {
            return
        }

        if (message.targetType == ConversationType.single) {
            val userInfo = message.targetInfo as UserInfo
            val targetId = userInfo.userName
            if (targetId != userName) {
                return
            }
        }
        when (message.contentType) {
            ContentType.text -> {
                if (message.direct == MessageDirect.send) {
                    list.add(ChatBean(message, AppConfig.TEXT_SEND))
                } else {
                    list.add(ChatBean(message, AppConfig.TEXT_RECEIVE))
                }
            }
            ContentType.image -> TODO()
            ContentType.voice -> TODO()
            ContentType.location -> TODO()
            ContentType.video -> TODO()
            ContentType.eventNotification -> TODO()
            ContentType.custom -> TODO()
            ContentType.unknown -> TODO()
            ContentType.file -> TODO()
            ContentType.prompt -> TODO()
        }

        adapter?.notifyItemInserted(list.size - 1)
        handler.sendEmptyMessageDelayed(SCROLL_BOTTOM, 100)
    }


    override fun initView() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true); //设置返回键可用
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return true
    }
}