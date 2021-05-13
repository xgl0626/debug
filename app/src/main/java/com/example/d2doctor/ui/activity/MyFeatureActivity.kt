package com.example.d2doctor.ui.activity

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.d2doctor.R
import com.example.d2doctor.ui.adapter.FeatureRvAdapter
import com.example.d2doctor.ui.adapter.FeatureRvAdapter.Companion.APPLY
import com.example.d2doctor.ui.adapter.FeatureRvAdapter.Companion.COMPLETE
import com.example.d2doctor.ui.adapter.FeatureRvAdapter.Companion.JUDGE_BUTTON
import com.example.d2doctor.ui.adapter.FeatureRvAdapter.Companion.NO_BUTTON
import com.example.d2doctor.utils.provideFakeData
import kotlinx.android.synthetic.main.activity_my_feature.*
import kotlinx.android.synthetic.main.fragment_messages.*
import kotlin.math.abs

class MyFeatureActivity : AppCompatActivity() {
    lateinit var adapter: FeatureRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_feature)
        initView()
        initData()
    }

    private fun initView() {
        //首次进入的情况为发单界面
        adapter = FeatureRvAdapter(this, NO_BUTTON)
        rv_my_feature.adapter = adapter
        rv_my_feature.layoutManager = LinearLayoutManager(this)
        btn_send.setOnClickListener {
            adapter.btnString = NO_BUTTON
            refreshRv()
            resetButtonColor(it)
        }

        btn_finish.setOnClickListener {
            adapter.btnString = APPLY
            refreshRv()
            resetButtonColor(it)
        }

        btn_complete.setOnClickListener {
            adapter.btnString = JUDGE_BUTTON
            refreshRv()
            resetButtonColor(it)
        }

        btn_hunt.setOnClickListener {
            adapter.btnString = COMPLETE
            refreshRv()
            resetButtonColor(it)
        }
    }

    private fun initData(){
        adapter.dataList = provideFakeData()
        refreshRv()
        adapter.notifyDataSetChanged()
    }

    private fun refreshRv(){
        val animator = ValueAnimator.ofFloat(0f, 100f).apply {
            duration = 1000
            start()
        }
        animator.addUpdateListener {
            it?.let { va ->
                rv_my_feature.alpha = (abs(va.animatedValue as Float) - 50f) / 50f
            }
        }
        adapter.notifyDataSetChanged()
    }

    private fun resetButtonColor(view: View){
        val unSelect = ContextCompat.getDrawable(this, R.drawable.shape_btn_unselcet)
        btn_send.background = unSelect
        btn_hunt.background = unSelect
        btn_complete.background = unSelect
        btn_finish.background = unSelect

        view.background = ContextCompat.getDrawable(this, R.drawable.shape_btn_select)
    }
}