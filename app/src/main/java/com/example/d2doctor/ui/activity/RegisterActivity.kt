package com.example.d2doctor.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.d2doctor.R
import com.example.d2doctor.ui.viewmodel.LoginOrRegisterViewModel
import com.example.d2doctor.utils.AddIconImage
import com.example.d2doctor.utils.ClickButtonAnimatorUtils
import com.example.d2doctor.utils.Toast.toast
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.activity_rigister.*


class RegisterActivity : AppCompatActivity() {
    companion object {
        const val LEFT = 0
    }


    private val viewModel by lazy {
        ViewModelProviders.of(this).get(LoginOrRegisterViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rigister)
        initView()
        initData()
    }

    private fun initView() {
        AddIconImage.setImageViewToEditText(
            R.drawable.ic_acticity_login_name,
            et_activity_register_username,
            LEFT
        )
        AddIconImage.setImageViewToEditText(
            R.drawable.ic_activity_login_psw,
            et_activity_register_psw,
            LEFT
        )
        AddIconImage.setImageViewToEditText(
            R.drawable.ic_activity_login_psw,
            et_activity_register_psw2,
            LEFT
        )
    }

    private fun initData() {

        bt_activity_register_register.setOnClickListener {
            val name = et_activity_register_username.text.toString()
            val psw = et_activity_register_psw.text.toString()
            val psw2 = et_activity_register_psw2.text.toString()
            if (name.trim() != "" && psw.trim() != "" && psw2.trim() != "") {
                viewModel.registerCallBack(name, psw, psw2)
            } else {
                toast("用户名或者密码不能为空")
            }
        }
        viewModel.result.observe(this, Observer {
            if (it) {
                changeToActivity(MainActivity())
                finish()
            }
        })
    }

    private fun changeToActivity(activity: Activity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}