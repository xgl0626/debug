package com.example.d2doctor.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.d2doctor.bean.MessagesTestData

class HomeViewModel : ViewModel() {
    val homeMessageData = MutableLiveData<List<MessagesTestData>>()

    fun getHomeData() {
        val data = ArrayList<MessagesTestData>()
        data.add(MessagesTestData("xlgxlg"))
        homeMessageData.value = data
    }

}