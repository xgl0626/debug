package com.example.d2doctor.ui.widget.banner

import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager

/**
 * @Author: 徐国林
 * @ClassName: ViewPagerScroller
 * @Description:
 * @Date: 2020/8/29 17:10
 */
class ViewPagerScroller : Scroller {
    private var mScrollDuration = 1000 // 滑动速度

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, interpolator: Interpolator?) : super(context, interpolator) {}
    constructor(context: Context?, interpolator: Interpolator?, flywheel: Boolean) : super(
        context,
        interpolator,
        flywheel
    ) {
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration)
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration)
    }

    fun initViewPagerScroll(viewPager: ViewPager?) {
        try {
            val mScroller = ViewPager::class.java.getDeclaredField("mScroller")
            mScroller.isAccessible = true
            mScroller[viewPager] = this
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 设置速度速度
     *
     * @param duration
     */
    fun setScrollDuration(duration: Int) {
        mScrollDuration = duration
    }
}