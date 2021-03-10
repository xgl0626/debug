package com.example.d2doctor.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.d2doctor.R
import com.example.d2doctor.ui.adapter.FragmentsAdapter
import com.example.d2doctor.ui.base.BaseActivity
import com.example.d2doctor.ui.fragment.DataFragment
import com.example.d2doctor.ui.fragment.HomeFragment
import com.example.d2doctor.ui.fragment.MessageFragment
import com.example.d2doctor.ui.fragment.PersonFragment
import com.example.d2doctor.utils.Toast
import com.example.weying.utils.ImmersedStatusbarUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {
        initViewPager()
    }

    private fun initViewPager() {
        val fragmentList = ArrayList<Fragment>()
        fragmentList.add(HomeFragment())
        fragmentList.add(DataFragment())
        fragmentList.add(MessageFragment())
        fragmentList.add(PersonFragment())
        val fragmentAdapter = FragmentsAdapter(fragmentList, supportFragmentManager, 3)
        viewpager.adapter = fragmentAdapter
        viewpager.addOnPageChangeListener(this)
        bottom_navigationview.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> viewpager.currentItem = 0
                R.id.navigation_data -> viewpager.currentItem = 1
                R.id.navigation_message -> viewpager.currentItem = 2
                R.id.navigation_person -> viewpager.currentItem = 3
                else -> Toast.toast("error")
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        bottom_navigationview.menu.getItem(position).isChecked = true
    }

}