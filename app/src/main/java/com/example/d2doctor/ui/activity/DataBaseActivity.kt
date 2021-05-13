package com.example.d2doctor.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.d2doctor.R
import com.example.d2doctor.ui.adapter.FeatureRvAdapter
import com.example.d2doctor.utils.provideFakeData
import kotlinx.android.synthetic.main.activity_data_base.*

class DataBaseActivity : AppCompatActivity() {

    lateinit var adapter: FeatureRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)

        initView()
    }

    private fun initView(){
        adapter = FeatureRvAdapter(this, "解决对象: Lt")
        adapter.dataList = provideFakeData()
        rv_database.adapter = adapter
        rv_database.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }
}