package com.myassignment.fooddeliveryapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myassignment.fooddeliveryapp.model.FoodList
import com.myassignment.fooddeliveryapp.retrofit.MyApi
import com.myassignment.fooddeliveryapp.retrofit.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class FoodListNetworkDataSource (private val apiService: MyApi, private val compositeDisposable: CompositeDisposable) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
    get() = _networkState

    private val _downloadedFoodListResponse = MutableLiveData<List<FoodList.FoodInfo>>()
    val downloadedFoodListResponse: LiveData<List<FoodList.FoodInfo>>
    get() = _downloadedFoodListResponse

    private val _downloadedNoticeListResponse = MutableLiveData<List<FoodList.NoticeInfo>>()
    val downloadedNoticeListResponse: LiveData<List<FoodList.NoticeInfo>>
        get() = _downloadedNoticeListResponse

    fun fetchFoodList() {
        _networkState.postValue(NetworkState.LOADING)

        try{
            compositeDisposable.add(apiService.foodlists
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _downloadedFoodListResponse.postValue(it.result)
                        _networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e("FoodListNetworkDataSource",it.message)
                    }
                )
            )
        }catch (e: Exception){
            Log.e("FoodListNetworkDataSource",e.message)
        }
    }

    fun fetchNoticeList() {
        _networkState.postValue(NetworkState.LOADING)

        try{
            compositeDisposable.add(apiService.foodlists
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        _downloadedNoticeListResponse.postValue(it.notice)
                        _networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e("FoodListNetworkDataSource",it.message)
                    }
                )
            )
        }catch (e: Exception){
            Log.e("FoodListNetworkDataSource",e.message)
        }
    }
}