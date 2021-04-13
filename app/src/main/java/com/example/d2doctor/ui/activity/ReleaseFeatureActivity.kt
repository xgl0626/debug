package com.example.d2doctor.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.d2doctor.R
import com.example.d2doctor.utils.SelViewChangeBackHelper
import com.example.d2doctor.utils.Toast.toast
import kotlinx.android.synthetic.main.activity_release_feature.*

class ReleaseFeatureActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var helper: SelViewChangeBackHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_release_feature)

        helper = SelViewChangeBackHelper(
            listOf(
                tv_android,
                tv_cpp,
                tv_py,
                tv_swift,
            ),
            R.drawable.shape_btn_select,
            R.drawable.shape_btn_unselcet
        )

        tv_android.setOnClickListener(this)
        tv_py.setOnClickListener(this)
        tv_cpp.setOnClickListener(this)
        tv_swift.setOnClickListener(this)

        btn_release.setOnClickListener {
            Toast.makeText(this,"发布成功", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun onClick(v: View?) {
        v?.let {
            helper.changeViewBack(v)
        }
    }
}