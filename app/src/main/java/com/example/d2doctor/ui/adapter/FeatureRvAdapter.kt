package com.example.d2doctor.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.d2doctor.R
import com.example.d2doctor.bean.Feature
import com.example.d2doctor.ui.activity.FeatureDetail2Activity
import com.example.d2doctor.ui.activity.FeatureDetailActivity
import kotlinx.android.synthetic.main.item_feature.view.*

/**
 * Author: RayleighZ
 * Time: 2021-03-29 1:20
 */
open class FeatureRvAdapter(val activity: AppCompatActivity, var btnString: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val NO_BUTTON = ""
        const val JUDGE_BUTTON = "评价"
        const val APPLY = "同意"
        const val COMPLETE = "订单完成"
    }

    var dataList: List<Feature>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_feature, parent, false)
        return object : RecyclerView.ViewHolder(itemView) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dataList?.let { dataList ->
            holder.itemView.apply {
                tv_featrue_nickname.text = dataList[position].nickName
                tv_feature_item_content.text = dataList[position].content
                tv_featrue_item_time.text = dataList[position].time
                tv_feature_item_price.text = dataList[position].money
                btn_feature_operate.visibility = View.VISIBLE
                if (btnString == "") btn_feature_operate.visibility = View.GONE else btn_feature_operate.text = btnString
            }
        }
        holder.itemView.setOnClickListener {
            activity.startActivity(
                Intent(activity, FeatureDetail2Activity::class.java)
            )
        }
    }

    override fun getItemCount(): Int {
        if (dataList.isNullOrEmpty()){
            return 0
        } else {
            dataList?.let {
                return it.size
            }
        }
        return 0
    }
}