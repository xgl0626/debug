package com.example.d2doctor.ui.widget.banner

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

/**
 * @Author: 徐国林
 * @ClassName: Banner
 * @Description:
 * @Date: 2020/8/29 17:06
 */
class Banner : FrameLayout {

    /**
     * 确定小圆点的位置
     * 中间or右边
     */
    val CENTER = 1
    val RIGHT = 5

    private var mContext: Context? = null

    /**
     * 小圆点的drawable
     * 下标0的为没有被选中的
     * 下标1的为已经被选中的
     */
    private val mDot = IntArray(2)

    /**
     * 存放小圆点的Group
     */
    private var mDotGroup: LinearLayout? = null

    /**
     * 存放没有被选中的小圆点Group和已经被选中小圆点
     */
    private var mFrameLayout: FrameLayout? = null

    /**
     * 选中的小圆点
     */
    private var mSelectedDot: View? = null
    private var mPager: BannerViewPager? = null
    private var mAdapter: BannerPagerAdapter<*>? = null



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(c: Context) :
            this(c, null) {
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?) :
            this(context, attrs, 0)


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            this(context, attrs, defStyleAttr, 0)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context!!, attrs, defStyleAttr, defStyleRes) {
        mContext=context
        init()
    }

    private fun init() {
        mPager = BannerViewPager(mContext)
        addView(mPager)
        /**
         * 实例化两个Group
         */
        mFrameLayout = FrameLayout(mContext!!)
        mDotGroup = LinearLayout(mContext)
        /**
         * 设置小圆点Group的方向为水平
         */
        mDotGroup!!.orientation = LinearLayout.HORIZONTAL
        /**
         * 如果不设置则小圆点在中间
         */
        mDotGroup!!.gravity = CENTER or Gravity.BOTTOM
        /**
         * 两个Group的大小都为match_parent
         */
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        /**
         * 首先添加小圆点的父Group
         */
        mFrameLayout!!.addView(mDotGroup, params)
        /**
         * 在添加小圆点
         */
        addView(mFrameLayout, params)
        mPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                /**
                 * 获取到当前position
                 */
                var position = position
                position %= mAdapter!!.size
                /**
                 * 判断如果当前的position不是最后一个,那么就设置偏移量来实现被选中小圆点的滑动效果
                 */
                if (mSelectedDot != null && position != mAdapter!!.size - 1 && mAdapter!!.size != 1) {
                    val dx = mDotGroup!!.getChildAt(1).x - mDotGroup!!.getChildAt(0).x
                    mSelectedDot!!.translationX = position * dx + positionOffset * dx
                }
            }

            override fun onPageSelected(position: Int) {
                var position = position
                position %= mAdapter!!.size
                /**
                 * 如果已经是最后一个,那么则直接把小圆点定位到那,不然滑动效果会出错
                 */
                if (mSelectedDot != null && position == mAdapter!!.size - 1 && mAdapter!!.size != 1) {
                    val dx = mDotGroup!!.getChildAt(1).x - mDotGroup!!.getChildAt(0).x
                    mSelectedDot!!.translationX = position * dx
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun setAdapter(adapter: BannerPagerAdapter<*>?): com.example.d2doctor.ui.widget.banner.Banner {
        mAdapter = adapter
        //viewpager添加pageradapter进行翻页
        mPager!!.adapter = mAdapter
        //viewpager设置当前item
        mPager!!.currentItem = mAdapter!!.size
        if (mDotGroup!!.childCount != 0) mDotGroup!!.removeAllViews()
        val dotParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        /**
         * 未选中小圆点的间距
         */
        dotParams.rightMargin = 12
        /**
         * 创建未选中的小圆点
         */
        //根据adapter的size大小设置点数多少
        for (i in 0 until mAdapter!!.size) {
            val iv = ImageView(mContext)
            iv.setImageDrawable(mContext!!.resources.getDrawable(mDot[0]))
            iv.layoutParams = dotParams
            mDotGroup!!.addView(iv)
        }
        /**
         * 添加到任务栈,当前所有任务完成之后添加已经选中的小圆点
         */
        post {
            if (mSelectedDot == null) {
                val iv = ImageView(mContext)
                iv.setImageDrawable(mContext!!.resources.getDrawable(mDot[1]))
                val params = LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                /**
                 * 设置选中小圆点的左边距
                 */
                /**
                 * 设置选中小圆点的左边距
                 */
                params.leftMargin = mDotGroup!!.getChildAt(0).x.toInt()
                params.gravity = Gravity.BOTTOM
                mFrameLayout!!.addView(iv, params)
                mSelectedDot = mFrameLayout!!.getChildAt(1)
            }
        }
        return this
    }

    fun setOnItemClickListener(l: BannerPagerAdapter.onItemClickListener?):Banner {
        mAdapter!!.setOnItemClickListener(l)
        return this
    }

    fun startAutoPlay(): Banner {
        if (!mPager!!.isAutoPlay) mPager!!.startAutoPlay()
        return this
    }

    fun stopAutoPlay() {
        mPager!!.stopAutoPlay()
    }

    fun setTime(time: Int): Banner? {
        mPager!!.setTime(time)
        return this
    }

    fun setDot(vararg dots: Int): Banner? {
        mDot[0] = dots[0]
        mDot[1] = dots[1]
        return this
    }

    fun setCurrentPager(page: Int) {
        mPager!!.currentItem = page
    }

    fun setCurrentPager(page: Int, isSmooth: Boolean) {
        mPager!!.setCurrentItem(page, isSmooth)
    }

    fun setScrollDuration(duration: Int): Banner? {
        mPager!!.setScrollDuration(duration)
        return this
    }

    fun setDotGravity(gravity: Int): Banner? {
        mDotGroup!!.gravity = gravity or Gravity.BOTTOM
        val density = mContext!!.resources.displayMetrics.density
        if (gravity == CENTER) {
            mFrameLayout!!.setPadding(0, 0, 0, density.toInt() * 10)
        } else {
            mFrameLayout!!.setPadding(0, 0, 0, density.toInt() * 10)
        }
        return this
    }
}