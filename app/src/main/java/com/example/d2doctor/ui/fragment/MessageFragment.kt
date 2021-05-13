package com.example.d2doctor.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import cn.jpush.im.android.api.model.Conversation
import com.example.d2doctor.R
import com.example.d2doctor.config.AppConfig
import com.example.d2doctor.ui.activity.AddFriendsActivity
import com.example.d2doctor.ui.activity.ChatActivity
import com.example.d2doctor.ui.activity.ReceiveFriendActivity
import com.example.d2doctor.ui.adapter.MessagesFragmentAdapter
import com.example.d2doctor.ui.viewmodel.MessageViewModel
import kotlinx.android.synthetic.main.fragment_messages.*


class MessageFragment : Fragment() {

    private lateinit var messageViewModel: MessageViewModel
    private var messagesAdapter: MessagesFragmentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageViewModel =
            ViewModelProviders.of(this).get(MessageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_messages, container, false)
        setHasOptionsMenu(true)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
        initClick()
    }

    private fun initClick() {
        tv_receive_friend.setOnClickListener {
            changeToActivity(ReceiveFriendActivity())
        }
    }

    private fun initView() {
        (activity as AppCompatActivity).setSupportActionBar(toolBar)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        messagesAdapter = this.activity?.let {
            MessagesFragmentAdapter(it).apply {
                onMsgClickListener = { username ->
                    val intent = Intent(this.activity, ChatActivity::class.java)
                    intent.putExtra("username", username)
                    intent.putExtra("message", AppConfig.SINGLEMESSAGE)
                    this.activity.startActivity(intent)
                }
            }
        }
        recyclerView.adapter = messagesAdapter

    }

    private fun initData() {
        messageViewModel.getChatTopicList()
        srl_main.setOnRefreshListener {
            messageViewModel.getChatTopicList()
        }
        messageViewModel.messageData.observe(viewLifecycleOwner, Observer {
            messagesAdapter?.addData(it)
            srl_main.isRefreshing = false
        })
    }

    private fun changeToActivity(activity: Activity) {
        val intent = Intent(this.activity, activity::class.java)
        this.activity?.startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_message_add_friend -> {
                changeToActivity((AddFriendsActivity()))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.message_menu, menu)
    }
}