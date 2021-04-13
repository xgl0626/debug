package com.example.d2doctor.ui.adapter

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.d2doctor.R
import com.example.d2doctor.bean.FeatureDB
import com.example.d2doctor.ui.activity.DataBaseActivity
import kotlinx.android.synthetic.main.item_feature_db.view.*

/**
 * Author: RayleighZ
 * Time: 2021-03-29 8:00
 */
class FeatureDBRvAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val dataList = ArrayList<FeatureDB>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_feature_db, parent, false)
        return object : RecyclerView.ViewHolder(itemView){ }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = holder.itemView
        val dataDB = dataList[position]
        itemView.apply {
            tv_feature_db_item.text = dataDB.dbName
            iv_feature_db_item.setColorFilter(Color.parseColor(dataDB.backColor))
        }
        itemView.setOnClickListener {
            itemView.context.startActivity(
                Intent(itemView.context, DataBaseActivity::class.java)
            )
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(data: List<FeatureDB>){
        dataList.clear()
        dataList.addAll(data)
        this.notifyDataSetChanged()
    }
}