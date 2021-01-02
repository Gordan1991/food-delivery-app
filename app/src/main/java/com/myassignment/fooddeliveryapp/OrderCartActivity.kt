package com.myassignment.fooddeliveryapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.myassignment.fooddeliveryapp.adapter.CartListAdapter
import com.myassignment.fooddeliveryapp.model.FoodList
import kotlinx.android.synthetic.main.ordercart_activity.*

class OrderCartActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ordercart_activity)

        //Get Cart List
        val cartList = this.intent.getParcelableArrayListExtra<FoodList.FoodInfo>("cart_list")
        displayData(cartList)
    }

    private fun displayData(foodList: ArrayList<FoodList.FoodInfo>?) {
        //UI View
        val cartMapList = foodList?.groupingBy { it }?.eachCount()
        cartlist_rv.setHasFixedSize(true)
        cartlist_rv.layoutManager = LinearLayoutManager(this)
        val adapter = CartListAdapter(this, cartMapList!!)
        cartlist_rv.adapter = adapter

        //Calculate Total Amount
        val total = cartMapList.entries.map { it.key.price.toInt() * it.value }.sum()
        total_tv.text = "Total :$${total}"
    }
}