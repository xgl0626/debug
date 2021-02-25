package com.example.d2doctor.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.d2doctor.R
import com.example.d2doctor.ui.viewmodel.MessageViewModel
import com.example.d2doctor.utils.Toast
import kotlinx.android.synthetic.main.activity_add_friends.*

/**
 * @Author: 徐国林
 * @ClassName: AddFriendsActivity
 * @Description:
 * @Date: 2020/9/12 13:58
 */
class AddFriendsActivity : AppCompatActivity() {
    private lateinit var messageViewModel: MessageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friends)
        messageViewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
        initView()
        initData()

    }

    private fun initData() {
        tv_add_friend.setOnClickListener {
            messageViewModel.addFriend(
                et_add_friend_username.text.toString(),
                et_add_friend_content.text.toString()
            )
        }
        messageViewModel.backResult.observe(this, Observer {
            if (it)
                finish()
            else
                Toast.toast("未搜索到此人")
        })
    }

    private fun initView() {

    }
}