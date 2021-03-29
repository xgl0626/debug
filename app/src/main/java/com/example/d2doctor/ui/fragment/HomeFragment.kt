package com.example.d2doctor.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.d2doctor.R
import com.example.d2doctor.ui.activity.SearchActivity
import com.example.d2doctor.ui.adapter.BannerAdapter
import com.example.d2doctor.ui.adapter.FeatureRvAdapter
import com.example.d2doctor.ui.adapter.HomeMessagesAdapter
import com.example.d2doctor.ui.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private var messageItemAdapter: HomeMessagesAdapter? = null
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: FeatureRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initClick()
        initData()
    }

    private fun initClick() {
//        et_fragment_home_search.setOnClickListener {
//            changeToActivity(SearchActivity())
//        }
        iv_fragment_home_search.setOnClickListener {
            startActivity(Intent(this.context, SearchActivity::class.java))
        }
    }

    private fun initView() {
        rv_fragment_home.layoutManager = LinearLayoutManager(this.context)
        adapter = FeatureRvAdapter(this.activity as AppCompatActivity)
        rv_fragment_home.adapter = adapter
        viewModel.homeMessageData.observe(this.viewLifecycleOwner) {
            it?.let {
                adapter.dataList = it
                adapter.notifyDataSetChanged()
            }
        }
        this.context?.let {
            val bannerAdapter = BannerAdapter(it)
            bannerAdapter.setData(
                listOf(
                    "https://raynote.oss-cn-chengdu.aliyuncs.com/Banner.png"
                )
            )
            vp_home_banner.adapter = bannerAdapter
        }
//        AddIconImage.setImageViewToEditText(R.drawable.ic_search, et_fragment_home_search, 1)
//        val layoutManager = LinearLayoutManager(context)
//        recyclerView.layoutManager = layoutManager
//        messageItemAdapter = this.context?.let { HomeMessagesAdapter(it) }
//        recyclerView.adapter = messageItemAdapter

    }

    private fun initData() {
        viewModel.getHomeData()
//        srl_main.setOnRefreshListener {
//            viewModel.getHomeData()
//        }
//        viewModel.getHomeData()
//        viewModel.homeMessageData.observe(viewLifecycleOwner, Observer {
//            messageItemAdapter?.addData(it)
//            srl_main.isRefreshing = false
//        })
    }

    private fun changeToActivity(activity: Activity) {
        val intent = Intent(this.activity, activity::class.java)
        this.activity?.startActivity(intent)
    }
}