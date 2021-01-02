package com.myassignment.fooddeliveryapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myassignment.fooddeliveryapp.R
import com.myassignment.fooddeliveryapp.model.FoodList


class FoodListAdapter(internal var context: Context, internal var foodList:List<FoodList.FoodInfo>, callback: AdapterInterface): RecyclerView.Adapter<FoodListViewHolder>()  {

    interface AdapterInterface {
        fun addCartButtonPressed(item: FoodList.FoodInfo)
    }
    val mCallback : AdapterInterface = callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.foodlists_layout, parent, false)
        return FoodListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodListViewHolder, position: Int) {
        holder.title_tv.text = foodList[position].name
        holder.price_btn.text = "$" + foodList[position].price
        Glide.with(this.context)
            .load(foodList[position].image)
            .into(holder.photo_img)

        holder.price_btn.setOnClickListener {
            mCallback.addCartButtonPressed(foodList[position])
        }
    }
}