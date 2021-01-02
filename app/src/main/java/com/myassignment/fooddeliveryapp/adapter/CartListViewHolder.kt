package com.myassignment.fooddeliveryapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cartlists_layout.view.*
import kotlinx.android.synthetic.main.foodlists_layout.view.photo_img
import kotlinx.android.synthetic.main.foodlists_layout.view.title_tv

class CartListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title_tv = itemView.title_tv
    val price_tv = itemView.price_tv
    val photo_img = itemView.photo_img
    val quantity_tv = itemView.quantity_tv
}