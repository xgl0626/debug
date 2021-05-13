package com.example.d2doctor.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.d2doctor.R
import kotlinx.android.synthetic.main.item_hunter.view.*

/**
 * Author: RayleighZ
 * Time: 2021-03-29 10:42
 */
class HunterAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_hunter, parent, false)
        return object : RecyclerView.ViewHolder(itemView){ }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.apply {
            tv_hunter_price.text = "${position}元"
            tv_hunter_time.text = "2021-03-29 12:12"
        }
    }

    override fun getItemCount(): Int {
        return 10
    }
}