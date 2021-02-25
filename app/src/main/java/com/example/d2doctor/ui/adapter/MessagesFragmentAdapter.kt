package com.example.d2doctor.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.jpush.im.android.api.model.Conversation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.d2doctor.R
import com.example.d2doctor.ui.widget.CircleImageView

/**
 * @Author: 徐国林
 * @ClassName: MessagesAdapter
 * @Description:
 * @Date: 2020/9/2 20:02
 */
class MessagesFragmentAdapter(val activity: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HEADER = 0
        const val LOAD = 1
    }

    var onMsgClickListener: ((String) -> Unit)? = null
    private var data = ArrayList<Conversation>()

    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<CircleImageView>(R.id.iv_avatar)
        val author = view.findViewById<TextView>(R.id.tv_authorName)
        val content = view.findViewById<TextView>(R.id.tv_messageContent)
        val time = view.findViewById<TextView>(R.id.tv_date)
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
                    R.layout.item_message,
                    parent,
                    false
                )
                return MessageViewHolder(view)
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
                    nomore.text = "你的信息为空"
                }
            }

            LOAD -> {
                val viewHolder = holder as MessageViewHolder
                viewHolder.apply {
                    Glide.with(avatar)
                        .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_image))
                        .load(R.drawable.ic_image).into(avatar)
                    author.text = data[position].title
                }
                viewHolder.itemView.setOnClickListener {
                    onMsgClickListener?.invoke(data[position].title)
                }
            }
        }
    }

    fun addData(newDataLists: List<Conversation>) {
        data.clear()
        initRefreshImages(newDataLists)
    }

    fun initRefreshImages(dataLists: List<Conversation>) {
        data.addAll(dataLists)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

}