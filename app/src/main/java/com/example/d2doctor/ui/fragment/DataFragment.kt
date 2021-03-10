package com.example.d2doctor.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.d2doctor.R
import com.example.d2doctor.ui.viewmodel.HomeViewModel

/**
 * @Author: xgl
 * @ClassName: DataFragment
 * @Description:
 * @Date: 2021/3/10 8:30
 */
class DataFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_data, container, false)
        return root
    }
}