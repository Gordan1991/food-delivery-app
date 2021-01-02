package com.myassignment.fooddeliveryapp.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getClient(): MyApi {
        val  httpClient : OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(MyInterceptor())
        }.build()

        return Retrofit.Builder()
            .baseUrl("https://8d3a3cc2-daa5-4be4-b0a3-fd7cafcf297f.mock.pstmn.io/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

}

//Create API Interceptor
class MyInterceptor: Interceptor {

    val SERVER_API_KEY = "PMAK-5fee967f48a05e0042940c1d-d85bdb9d00763f60a61fd3eaac968d0429"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .addHeader("X-Platform","Android")
            .addHeader("x-api-key", SERVER_API_KEY)
            .build()
        return chain.proceed(request)
    }
}