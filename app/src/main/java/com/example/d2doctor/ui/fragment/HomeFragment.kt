package com.example.d2doctor.ui.fragment

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.d2doctor.R
import com.example.d2doctor.ui.activity.SearchActivity
import com.example.d2doctor.ui.adapter.HomeMessagesAdapter
import com.example.d2doctor.ui.viewmodel.HomeViewModel
import com.example.d2doctor.utils.AddIconImage
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private var messageItemAdapter: HomeMessagesAdapter? = null
    private lateinit var viewModel: HomeViewModel
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
        et_fragment_home_search.setOnClickListener {
            changeToActivity(SearchActivity())
        }
    }

    private fun initView() {
        AddIconImage.setImageViewToEditText(R.drawable.ic_search, et_fragment_home_search, 1)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        messageItemAdapter = this.context?.let { HomeMessagesAdapter(it) }
        recyclerView.adapter = messageItemAdapter

    }

    private fun initData() {
        srl_main.setOnRefreshListener {
            viewModel.getHomeData()
        }
        viewModel.getHomeData()
        viewModel.homeMessageData.observe(viewLifecycleOwner, Observer {
            messageItemAdapter?.addData(it)
            srl_main.isRefreshing = false
        })
    }

    private fun changeToActivity(activity: Activity) {
        val intent = Intent(this.activity, activity::class.java)
        this.activity?.startActivity(intent)
    }
}