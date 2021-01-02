package com.myassignment.fooddeliveryapp.views.foodlist

import androidx.lifecycle.LiveData
import com.myassignment.fooddeliveryapp.model.FoodList
import com.myassignment.fooddeliveryapp.repository.FoodListNetworkDataSource
import com.myassignment.fooddeliveryapp.retrofit.MyApi
import com.myassignment.fooddeliveryapp.retrofit.NetworkState
import io.reactivex.disposables.CompositeDisposable

class FoodlistRepository(private val apiService: MyApi) {

    lateinit var foodlistNetworkDataSource: FoodListNetworkDataSource

    fun fetchFoodList(compositeDisposable: CompositeDisposable) : LiveData<List<FoodList.FoodInfo>> {
        foodlistNetworkDataSource = FoodListNetworkDataSource(apiService, compositeDisposable)
        foodlistNetworkDataSource.fetchFoodList()
        return foodlistNetworkDataSource.downloadedFoodListResponse
    }

    fun fetchNoticeList(compositeDisposable: CompositeDisposable) : LiveData<List<FoodList.NoticeInfo>> {
        foodlistNetworkDataSource = FoodListNetworkDataSource(apiService, compositeDisposable)
        foodlistNetworkDataSource.fetchNoticeList()
        return foodlistNetworkDataSource.downloadedNoticeListResponse
    }

    fun getFoodListNetworkState() : LiveData<NetworkState> {
        return foodlistNetworkDataSource.networkState
    }
}