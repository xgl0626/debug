package com.example.d2doctor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import cn.jpush.im.android.api.event.ContactNotifyEvent
import com.example.d2doctor.R
import kotlinx.android.synthetic.main.item_recive_friend.view.*

/**
 * @Author: xgl
 * @ClassName: ReceiveFriendAdapter
 * @Description:
 * @Date: 2021/2/17 11:01
 */
class ReceiveFriendAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HEADER = 0
        const val LOAD = 1
        const val r = 1
        const val f = 0
    }

    private var data = ArrayList<ContactNotifyEvent>()
    var onMsgItemClickListener: ((Int,Int) -> Unit)? = null
    class RFViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<AppCompatTextView>(R.id.tv_rf_name)
        val content = view.findViewById<AppCompatTextView>(R.id.tv_rf_content)
        val receive = view.findViewById<AppCompatTextView>(R.id.tv_rf_receive)
        val refused = view.findViewById<AppCompatTextView>(R.id.tv_rf_refused)
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomore = view.findViewById<TextView>(R.id.tv_no_more)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1)
            MyFriendAdapter.HEADER
        else
            MyFriendAdapter.LOAD
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOAD -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_recive_friend,
                    parent,
                    false
                )
                return RFViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_nomore,
                    parent,
                    false
                )
                return HeaderViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            HEADER -> {
                val viewHolder = holder as HeaderViewHolder
                viewHolder.apply {
                    nomore.text = "暂时没有好友申请哦"
                }
            }

            LOAD -> {
                val viewHolder = holder as RFViewHolder
                viewHolder.apply {
                    name.text = data[position].fromUsername
                    content.text = data[position].reason
                }
                viewHolder.itemView.apply {
                    tv_rf_receive.setOnClickListener {
                        onMsgItemClickListener?.invoke(r,position)
                    }
                    tv_rf_refused.setOnClickListener {
                        onMsgItemClickListener?.invoke(f,position)
                    }
                }
            }
        }
    }

    fun addData(newDataLists: List<ContactNotifyEvent>) {
        data.clear()
        initRefreshImages(newDataLists)
    }

    fun initRefreshImages(dataLists: List<ContactNotifyEvent>) {
        data.addAll(dataLists)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }
}