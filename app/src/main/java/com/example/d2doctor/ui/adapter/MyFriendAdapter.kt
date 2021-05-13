package com.example.d2doctor.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.jpush.im.android.api.model.UserInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.d2doctor.R
import com.example.d2doctor.ui.widget.CircleImageView

/**
 * @Author: 徐国林
 * @ClassName: AnswerAdapter
 * @Description:
 * @Date: 2020/9/2 17:26
 */
class MyFriendAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val HEADER = 0
        const val LOAD = 1
    }

    private var data = ArrayList<UserInfo>()
    var onMsgItemClickListener: ((Int) -> Unit)? = null
    class AnswerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<CircleImageView>(R.id.iv_avatar)
        val author = view.findViewById<TextView>(R.id.tv_authorName)
        val content = view.findViewById<TextView>(R.id.tv_answerContent)
        val time = view.findViewById<TextView>(R.id.tv_date)

    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomore = view.findViewById<TextView>(R.id.tv_no_more)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1)
            HEADER
        else
            LOAD
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOAD -> {
                val view = LayoutInflater.from(parent.context).inflate(
                    R.layout.item_home_answer,
                    parent,
                    false
                )
                return AnswerViewHolder(view)
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
                    nomore.text = "没有更多了"
                }
            }

            LOAD -> {
                val viewHolder = holder as AnswerViewHolder
                viewHolder.apply {
                    Glide.with(avatar)
                        .applyDefaultRequestOptions(RequestOptions().placeholder(R.mipmap.head_default))
                        .load(R.mipmap.head_default).into(avatar)
                    author.text = data[position].userName
                }
                viewHolder.itemView.setOnClickListener {
                    onMsgItemClickListener?.invoke(position)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

    fun addData(newDataLists: List<UserInfo>) {
        data.clear()
        initRefreshImages(newDataLists)
    }

    fun initRefreshImages(dataLists: List<UserInfo>) {
        data.addAll(dataLists)
        notifyDataSetChanged()
    }
}