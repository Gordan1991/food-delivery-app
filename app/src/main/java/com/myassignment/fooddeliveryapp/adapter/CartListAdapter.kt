package com.myassignment.fooddeliveryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myassignment.fooddeliveryapp.model.FoodList
import com.myassignment.fooddeliveryapp.R

class CartListAdapter(internal var context: Context, internal var foodList:Map<FoodList.FoodInfo, Int>): RecyclerView.Adapter<CartListViewHolder>()  {

    val foodInfoList = foodList.keys.toList()
    val cartQuantity = foodList.values.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cartlists_layout, parent, false)
        return CartListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {

        holder.title_tv.text = foodInfoList[position].name
        holder.price_tv.text = "$ ${foodInfoList[position].price}"
        holder.quantity_tv.text =  "Quantity x ${cartQuantity[position]}"

        Glide.with(this.context)
            .load(foodInfoList[position].image)
            .into(holder.photo_img)
    }
}