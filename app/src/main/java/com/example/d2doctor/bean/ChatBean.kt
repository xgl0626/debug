package com.example.d2doctor.bean

import cn.jpush.im.android.api.model.Message

/**
 * @Author: xgl
 * @ClassName: ChatBean
 * @Description:
 * @Date: 2021/2/18 20:20
 */
data class ChatBean(val message: Message,val itemType: Int = 1)
//itemtype=1为自己发的，默认右边，为0就是对方发的为0