package com.example.d2doctor.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.d2doctor.R
import com.example.d2doctor.ui.widget.banner.BannerPagerAdapter

/**
 * @Author: 徐国林
 * @ClassName: BannerAdapter
 * @Description:
 * @Date: 2020/8/29 17:04
 */
class BannerAdapter(val context:Context) : BannerPagerAdapter<String>() {
    private var imageUrls:List<String>?=null
    override fun setView(position: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.item_banner_imageview,null)
    }

    override fun setData(data: List<String>) {
        imageUrls=data
    }
}