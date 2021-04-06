package com.example.d2doctor.ui.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import cn.jiguang.api.JCoreInterface
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.callback.GetUserInfoCallback
import cn.jpush.im.android.api.model.UserInfo
import com.example.d2doctor.R
import com.example.d2doctor.config.AppConfig
import com.example.d2doctor.ui.activity.*
import com.example.d2doctor.ui.viewmodel.PersonViewModel
import com.example.d2doctor.utils.ClickButtonAnimatorUtils
import com.example.d2doctor.utils.MyApplication
import com.example.weying.utils.AvatarUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_person.*
import kotlinx.android.synthetic.main.item_message.*

class PersonFragment : Fragment(), View.OnClickListener {

    private lateinit var personViewModel: PersonViewModel
    private var rememberPassword: SharedPreferences = MyApplication.context.getSharedPreferences(
        "remember",
        Context.MODE_PRIVATE
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        personViewModel =
            ViewModelProviders.of(this).get(PersonViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_person, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        iv_person_avator.setOnClickListener(this)
        rl_person_single_data.setOnClickListener(this)
        rl_person_friend_list.setOnClickListener(this)
    }

    private fun initData() {
        JMessageClient.getUserInfo(
            rememberPassword.getString("name", ""),
            AppConfig.appKey,
            object : GetUserInfoCallback() {
                override fun gotResult(loadCode: Int, loadState: String?, userInfo: UserInfo?) {
                    Log.d(AppConfig.TAG, "$loadCode$loadCode $userInfo ")
                    if (loadCode == 0) {
                        userInfo?.apply {
                            tv_person_name.text = userName
                        }
                    }
                }
            })
        rl_person_set.setOnClickListener {
            changeToActivity(AboutMeActivity())
        }
        rl_person_about_me.setOnClickListener {
            changeToActivity(MyBagActivity())
        }
        tv_back_login.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ClickButtonAnimatorUtils.buttonAnim(tv_back_login,
                    object : ClickButtonAnimatorUtils.AnimEndCallBack {
                        override fun callback() {
                            JMessageClient.logout()
                            changeToActivity(LoginActivity())
                            activity?.finish()
                        }
                    })
            }
        }
    }

    private fun changeToActivity(activity: Activity) {
        val intent = Intent(this.activity, activity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_avatar -> {
                this.activity?.let { AvatarUtils.choicePhoto(it) }
            }
            R.id.rl_person_single_data -> {
                changeToActivity(PersonDataActivity())
            }
            R.id.rl_person_friend_list -> {
                changeToActivity(MyFriendActivity())
            }
        }
    }

}