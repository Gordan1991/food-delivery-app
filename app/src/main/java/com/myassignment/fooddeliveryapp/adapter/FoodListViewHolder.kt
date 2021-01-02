package com.myassignment.fooddeliveryapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.foodlists_layout.view.*

class FoodListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title_tv = itemView.title_tv
    val price_btn = itemView.price_btn
    val photo_img = itemView.photo_img
}