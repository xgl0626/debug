package com.example.d2doctor.ui.activity

import android.Manifest
import android.Manifest.permission.INTERNET
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.d2doctor.R
import com.example.d2doctor.ui.viewmodel.LoginOrRegisterViewModel
import com.example.d2doctor.utils.AddIconImage
import com.example.d2doctor.utils.ClickButtonAnimatorUtils
import com.example.d2doctor.utils.Toast
import com.example.weying.utils.ImmersedStatusbarUtils
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    companion object {
        const val permission = 1
        const val LEFT = 0
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(LoginOrRegisterViewModel::class.java)
    }

    val permissions =
        arrayOf(INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE)
    private var rememberPassword: SharedPreferences? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        initData()

    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, permissions, permission)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permission -> {
                //权限请求失败
                if (grantResults.size == permissions.size) {
                    for (result in grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            //弹出对话框引导用户去设置
                            showDialog();
                            Toast.toast("请求权限被拒绝")
                        }
                    }
                } else {
                    Toast.toast("已授权")
                }
            }
        }
    }

    //弹出提示框
    fun showDialog() {
        val dialog = AlertDialog.Builder(this)
            .setMessage("是否去设置权限？")
            .setPositiveButton("是") { dialog, which ->
                dialog.dismiss()
                goToAppSetting()
            }
            .setNegativeButton(
                "否"
            ) { dialog, p1 -> dialog?.dismiss() }
            .setCancelable(false)
            .show()
    }

    private fun goToAppSetting() {
        val intent = Intent()
        intent.action = ACTION_APPLICATION_DETAILS_SETTINGS;
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    private fun initView() {
        AddIconImage.setImageViewToEditText(
            R.drawable.ic_acticity_login_name,
            et_activity_login_username,
            LEFT
        )
        AddIconImage.setImageViewToEditText(
            R.drawable.ic_activity_login_psw,
            et_activity_login_psw,
            LEFT
        )
    }

    @SuppressLint("CommitPrefEdits")
    private fun initData() {
//        requestPermission()
        rememberPassword = getSharedPreferences("remember", MODE_PRIVATE)
        et_activity_login_username.setText(rememberPassword?.getString("name", ""))
        et_activity_login_psw.setText(rememberPassword?.getString("psw", ""))
        bt_activity_login_login.setOnClickListener {

            val name = et_activity_login_username.text.toString()
            val psw = et_activity_login_psw.text.toString()
            if (name != "" && psw != "") {
                viewModel.loginCallback(name, psw)
            } else
                Toast.toast("用户名或者密码不能为空")
        }
        tv_activity_login_register.setOnClickListener {
            changeToActivity(RegisterActivity())
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
}