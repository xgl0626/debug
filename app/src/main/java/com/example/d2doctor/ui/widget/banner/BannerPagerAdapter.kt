package com.example.d2doctor.ui.widget.banner

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * @Author: 徐国林
 * @ClassName: BannerPagerAdapter
 * @Description:
 * @Date: 2020/8/29 17:08
 */
abstract class BannerPagerAdapter<T> : PagerAdapter() {
    /**
     * size为当前banner实际的页数
     */
    var size = -1
    private var position = 0
    var l: onItemClickListener? = null
    abstract fun setData(data: List<T>)

    override fun getCount(): Int {
        return BANNER_SIZE
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var position = position
        position %= size //通过这句话来确定实际的position
        this.position = position
        /**
         * 和正常设置pagerAdapter一样的步骤
         */
        val p = position
        val view = setView(p)
        container.addView(view)
        view.setOnClickListener {
            if (l != null) {
                l!!.onClick(p)
            }
        }
        return view
    }

    override fun finishUpdate(container: ViewGroup) {
        val pager = container as ViewPager

        /* getcurrentitem 第一页=0
         * 这里获取到viewpager翻到多少页，例如data的size=4；第一张内容设置到viewpager的第5页
         * 当viewpager翻到99页为最后一页时，viewpager翻到size-1=3为第4页形成一个循环
         */
        var p = pager.currentItem
        if (p == 0) {
            p = size
            pager.setCurrentItem(p, false)
        } else if (p == BANNER_SIZE - 1) {
            p = size - 1
            pager.setCurrentItem(p, false)
        }
    }

     override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    interface onItemClickListener {
        fun onClick(position: Int)
    }

    fun setOnItemClickListener(l: onItemClickListener?) {
        this.l = l
    }

    abstract fun setView(position: Int): View
    fun getPosition(): Int {
        return position
    }

    companion object {
        /**
         * 把banner页的个数设置为100页,这样就达到了无限的效果
         */
        private const val BANNER_SIZE = 100
    }
}
