package com.myassignment.fooddeliveryapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.myassignment.fooddeliveryapp.R
import com.myassignment.fooddeliveryapp.model.FoodList
import kotlinx.android.synthetic.main.notice_photo_slider_item.view.*

class NoticePhotoSliderAdapter(private val context: Context, internal var noticeList:List<FoodList.NoticeInfo>) : PagerAdapter() {

    private var inflater: LayoutInflater? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return noticeList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.notice_photo_slider_item, null)
        Glide.with(this.context)
            .load(noticeList[position].image)
            .into(view.notice_img)
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

}