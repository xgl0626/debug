package com.example.d2doctor.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.d2doctor.R
import com.example.d2doctor.ui.adapter.HunterAdapter
import kotlinx.android.synthetic.main.activity_feature_detail.*

class FeatureDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_detail)
        initView()
    }

    fun initView(){
        val adapter = HunterAdapter()
        rv_activity_detail.adapter = adapter
        rv_activity_detail.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }
}