package com.example.d2doctor.ui.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.event.ContactNotifyEvent
import com.example.d2doctor.R
import com.example.d2doctor.ui.adapter.ReceiveFriendAdapter
import com.example.d2doctor.ui.base.BaseActivity
import com.example.d2doctor.ui.viewmodel.MessageViewModel
import com.example.d2doctor.ui.viewmodel.PersonViewModel
import com.example.d2doctor.ui.widget.banner.BannerPagerAdapter
import com.example.d2doctor.utils.L
import kotlinx.android.synthetic.main.activity_recive_friend.*
import org.greenrobot.eventbus.EventBus


/**
 * @Author: xgl
 * @ClassName: ContactNotifyEventReceiver
 * @Description:
 * @Date: 2021/1/30 16:29
 */

class ReceiveFriendActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_recive_friend
    }

    private lateinit var messageViewModel: MessageViewModel
    var list: MutableList<ContactNotifyEvent> = mutableListOf()
    var adapter: ReceiveFriendAdapter? = null

    override fun initData() {
        messageViewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
        L.v(friendApply.toString())
        list.addAll(friendApply)
        adapter = ReceiveFriendAdapter().apply {
            onMsgItemClickListener = { code, position ->
                when (code) {
                    ReceiveFriendAdapter.r -> {
                        messageViewModel.acceptFriend(list[position].fromUsername)
                    }
                    ReceiveFriendAdapter.f -> {
                        messageViewModel.refusedFriend(list[position].fromUsername)
                    }
                }
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter?.addData(list)
    }

    override fun initView() {

    }

}