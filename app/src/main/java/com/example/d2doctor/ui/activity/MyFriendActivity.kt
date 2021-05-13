package com.example.d2doctor.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.android.api.model.UserInfo
import com.example.d2doctor.R
import com.example.d2doctor.config.AppConfig
import com.example.d2doctor.ui.adapter.MyFriendAdapter
import com.example.d2doctor.ui.base.BaseActivity
import com.example.d2doctor.ui.viewmodel.MessageViewModel
import kotlinx.android.synthetic.main.activity_my_friend.*
import kotlinx.android.synthetic.main.activity_my_friend.recyclerView
import kotlinx.android.synthetic.main.activity_my_friend.srl_main
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @Author: xgl
 * @ClassName: MyFriendActivity
 * @Description:
 * @Date: 2021/2/17 13:16
 */

class MyFriendActivity : BaseActivity() {
    private lateinit var viewModel: MessageViewModel
    private var adapter: MyFriendAdapter? = null
    val friendList = ArrayList<UserInfo>()
    override fun getLayoutId(): Int {
        return R.layout.activity_my_friend
    }

    override fun initView() {
    }

    override fun initData() {
        viewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
        viewModel.getFriendList()
        srl_main.setOnRefreshListener {
            viewModel.getFriendList()
        }
        viewModel.friendList.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                friendList.clear()
                friendList.addAll(it)
                adapter?.addData(friendList)
                srl_main.isRefreshing = false
            }
        })
        adapter = MyFriendAdapter().apply {
            onMsgItemClickListener = { postition ->
                val intent = Intent(this@MyFriendActivity, ChatActivity::class.java)
                intent.putExtra("username", friendList[postition].userName)
                intent.putExtra("message", AppConfig.SINGLEMESSAGE)
                startActivity(intent)
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun changeToActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}