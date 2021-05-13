package com.example.d2doctor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.jpush.im.android.api.content.TextContent
import com.example.d2doctor.R
import com.example.d2doctor.bean.ChatBean
import com.example.d2doctor.ui.widget.CircleImageView
import com.example.d2doctor.utils.L
import kotlinx.android.synthetic.main.item_chat_text_send.view.*

/**
 * @Author: xgl
 * @ClassName: ChatMessageAdapter
 * @Description:
 * @Date: 2021/2/18 20:14
 */
class ChatMessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HEADER = 0
        const val LEFT = 1
        const val RIGHT = 2
    }

    private var data = ArrayList<ChatBean>()

    class ChatReceiveViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<CircleImageView>(R.id.iv_head)
        val content = view.findViewById<TextView>(R.id.tv_chat_msg)
        val time = view.findViewById<TextView>(R.id.tv_time)
    }

    class ChatSendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<CircleImageView>(R.id.iv_head)
        val content = view.findViewById<TextView>(R.id.tv_chat_msg)
        val time = view.findViewById<TextView>(R.id.tv_time)
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomore = view.findViewById<TextView>(R.id.tv_no_more)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1)
            HEADER
        else {
            return if (data[position].itemType == 0)
                LEFT
            else
                RIGHT
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LEFT -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_chat_text_receive,
                    parent,
                    false
                )
                return ChatReceiveViewHolder(view)
            }
            RIGHT -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_chat_text_send,
                    parent,
                    false
                )
                return ChatSendViewHolder(view)
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
                    nomore.text = "暂无消息"
                }
            }

            LEFT -> {
                val viewHolder = holder as ChatReceiveViewHolder
                viewHolder.apply {
                    content.text = (data[position].message.content as TextContent).text
                }
            }
            RIGHT -> {
                val viewHolder = holder as ChatSendViewHolder
                viewHolder.apply {
                    content.text = (data[position].message.content as TextContent).text
                }
            }
        }
    }

    fun addData(newDataLists: List<ChatBean>) {
        data.clear()
        initRefreshImages(newDataLists)
    }

    fun addData(chatBean: ChatBean) {
        data.add(chatBean)
        notifyDataSetChanged()
    }

    fun initRefreshImages(dataLists: List<ChatBean>) {
        data.addAll(dataLists)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

}