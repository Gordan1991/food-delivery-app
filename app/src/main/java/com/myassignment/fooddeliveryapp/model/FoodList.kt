package com.myassignment.fooddeliveryapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class FoodList {
    @Parcelize
    data class FoodInfo (
        var id: String = "",
        var name: String = "",
        var price: String = "",
        var image: String = ""
    ): Parcelable

    @Parcelize
    data class NoticeInfo (
        var id: String = "",
        var image: String = ""
    ): Parcelable

    class Response {
        var status: String = ""
        var result: List<FoodInfo> = emptyList()
        var notice: List<NoticeInfo> = emptyList()
    }
}