package com.myassignment.fooddeliveryapp.retrofit

import com.myassignment.fooddeliveryapp.model.FoodList
import io.reactivex.Observable
import retrofit2.http.GET

interface MyApi {
    @get:GET("foodlists")
    val foodlists:Observable<FoodList.Response>
}