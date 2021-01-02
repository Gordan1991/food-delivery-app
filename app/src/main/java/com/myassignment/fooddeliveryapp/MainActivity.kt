package com.myassignment.fooddeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.myassignment.fooddeliveryapp.adapter.FoodListAdapter
import com.myassignment.fooddeliveryapp.adapter.NoticePhotoSliderAdapter
import com.myassignment.fooddeliveryapp.model.FoodList
import com.myassignment.fooddeliveryapp.retrofit.MyApi
import com.myassignment.fooddeliveryapp.retrofit.NetworkState
import com.myassignment.fooddeliveryapp.retrofit.RetrofitClient
import com.myassignment.fooddeliveryapp.utils.ConstantUtils
import com.myassignment.fooddeliveryapp.views.foodlist.FoodListViewModel
import com.myassignment.fooddeliveryapp.views.foodlist.FoodlistRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , FoodListAdapter.AdapterInterface{

    private lateinit var viewModel: FoodListViewModel
    private lateinit var foodlistRepository: FoodlistRepository
    var selectList : ArrayList<FoodList.FoodInfo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Init API
        val mApi : MyApi = RetrofitClient.getClient()
        foodlistRepository = FoodlistRepository(mApi)

        //View Model
        viewModel = getViewModel()
        viewModel.foodList.observe(this, Observer {
            displayData(it)
        })
        viewModel.noticeList.observe(this, Observer {
            noticeSlider(it)
        })
        viewModel.networkState.observe(this, Observer {
            //Switch NetworkState
            when (it) {
                NetworkState.LOADING -> ConstantUtils.configToast(this,"Loading")
                NetworkState.LOADED ->  ConstantUtils.configToast(this, "Success")
                NetworkState.ERROR -> ConstantUtils.configToast(this,"Error")
                else -> {
                    ConstantUtils.configToast(this,"Nothing")
                }
            }
        })

        //Go To Shopping Cart Page
        shopping_cart_btn.setOnClickListener{
            val intent = Intent(this, OrderCartActivity::class.java)
            intent.putParcelableArrayListExtra("cart_list", selectList)
            startActivity(intent)
        }
    }

    private fun displayData(foodList: List<FoodList.FoodInfo>?) {
        //UI View
        foodlist_rv.setHasFixedSize(true)
        foodlist_rv.layoutManager = LinearLayoutManager(this)
        val adapter = FoodListAdapter(this, foodList!!, this)
        foodlist_rv.adapter = adapter
    }

    private fun noticeSlider(noticeList: List<FoodList.NoticeInfo>?) {
        val adapter = NoticePhotoSliderAdapter(this, noticeList!!)
        notice_photo_vp.adapter = adapter
        notice_tab.setupWithViewPager(notice_photo_vp,true)
    }

    private fun getViewModel(): FoodListViewModel {
        return ViewModelProviders.of(this, object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return FoodListViewModel(foodlistRepository) as T
            }
        })[FoodListViewModel::class.java]
    }

    override fun addCartButtonPressed(item: FoodList.FoodInfo) {
        selectList.add(item)
        if(selectList.size > 0){
            cart_quantity_tv.visibility = View.VISIBLE
            cart_quantity_tv.text = "${selectList.size}"
        }
    }
}
