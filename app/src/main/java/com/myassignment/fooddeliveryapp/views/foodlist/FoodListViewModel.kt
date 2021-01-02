package com.myassignment.fooddeliveryapp.views.foodlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myassignment.fooddeliveryapp.model.FoodList
import com.myassignment.fooddeliveryapp.retrofit.NetworkState
import io.reactivex.disposables.CompositeDisposable

class FoodListViewModel(private val foodlistRepository: FoodlistRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val foodList : LiveData<List<FoodList.FoodInfo>> by lazy {
        foodlistRepository.fetchFoodList(compositeDisposable)
    }

    val noticeList : LiveData<List<FoodList.NoticeInfo>> by lazy {
        foodlistRepository.fetchNoticeList(compositeDisposable)
    }

    val networkState : LiveData<NetworkState> by lazy {
        foodlistRepository.getFoodListNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}