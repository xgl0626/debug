package com.example.d2doctor.ui.widget.banner

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * @Author: 徐国林
 * @ClassName: BannerViewPager
 * @Description:
 * @Date: 2020/8/29 17:10
 */
class BannerViewPager @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    ViewPager(context!!, attrs) {
    private var SEND_TIME = 2000
    private var position = 0
    private val mPagerScroller: ViewPagerScroller = ViewPagerScroller(context)
    var isAutoPlay = false
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            position = currentItem + 1
            currentItem = position
            sendEmptyMessageDelayed(MSG_WHAT, SEND_TIME.toLong())
        }
    }

    fun setScrollDuration(duration: Int) {
        mPagerScroller.setScrollDuration(duration)
        mPagerScroller.initViewPagerScroll(this)
    }

    fun startAutoPlay(): BannerViewPager {
        isAutoPlay = true
        mHandler.sendEmptyMessageDelayed(MSG_WHAT, SEND_TIME.toLong())
        return this
    }

    @JvmName("isAutoPlay1")
    fun isAutoPlay(): Boolean {
        return isAutoPlay
    }

    fun stopAutoPlay() {
        isAutoPlay = false
        mHandler.removeMessages(MSG_WHAT)
    }

    fun setTime(time: Int): BannerViewPager {
        SEND_TIME = time
        return this
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_OUTSIDE) {
            Log.d("112233", "ok")
        }
        if (ev.action == MotionEvent.ACTION_DOWN) {
            stopAutoPlay()
        } else if (ev.action == MotionEvent.ACTION_UP) {
            startAutoPlay()
        }
        return super.dispatchTouchEvent(ev)
    }

    companion object {
        private const val MSG_WHAT = -1
    }

    init {
        mPagerScroller.initViewPagerScroll(this)
    }
}