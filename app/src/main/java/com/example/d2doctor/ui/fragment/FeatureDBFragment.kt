package com.example.d2doctor.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.d2doctor.R
import com.example.d2doctor.bean.FeatureDB
import com.example.d2doctor.ui.activity.SearchActivity
import com.example.d2doctor.ui.adapter.FeatureDBRvAdapter
import kotlinx.android.synthetic.main.activity_person_data.*
import kotlinx.android.synthetic.main.fragment_feature_db.*

/**
 * Author: RayleighZ
 * Time: 2021-03-29 1:43
 */
class FeatureDBFragment: Fragment() {
    lateinit var rvAdapter: FeatureDBRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(container?.context).inflate(R.layout.fragment_feature_db, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    fun initView(){
        rv_fragment_featrue_db.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvAdapter = FeatureDBRvAdapter()
        rv_fragment_featrue_db.adapter = rvAdapter
        tv_fragment_feature_db_search.setOnClickListener {
            startActivity(
                Intent(this.context, SearchActivity::class.java)
            )
        }
    }

    fun initData(){
        val featureDBList = listOf(
            FeatureDB(),
            FeatureDB(
                dbName = "Python",
                backColor = "#ffffff"
            ),
            FeatureDB(
                dbName = "Kotllin",
                backColor = "#FFF176"
            ),
            FeatureDB(
                dbName = "C++",
                backColor = "#F5F5DC"
            ),
            FeatureDB(
                dbName = "IOS",
                backColor = "#D3D3D3"
            ),
            FeatureDB(
                dbName = "Object C",
                backColor = "#FAFAD2"
            )
        )
        rvAdapter.setData(featureDBList)
    }
}