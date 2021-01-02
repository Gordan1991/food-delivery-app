package com.myassignment.fooddeliveryapp.retrofit

enum class Status {
    LOADING,
    SUCCESS,
    FAIL
}

class NetworkState(val status: Status, val msg: String) {

    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.LOADING, "Loading")
            ERROR = NetworkState(Status.FAIL, "Error occurs")
        }
    }
}